package com.myshop.order.services.Impl;

import com.myshop.commonDtos.dto.OrderRequestDto;
import com.myshop.commonDtos.events.enums.OrderStatus;
import com.myshop.order.dto.OrderDto;
import com.myshop.order.dto.UserDto;
import com.myshop.order.entities.Order;
import com.myshop.order.exceptions.wrapper.OrderNotFoundException;
import com.myshop.order.repositories.OrderRepository;
import com.myshop.order.helper.OrderMappingHelper;
import com.myshop.order.services.OrderService;
import com.myshop.order.services.OrderStatusPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    private final OrderStatusPublisher orderStatusPublisher;

    private final RestTemplate restTemplate;


    @Override
    public OrderDto save(OrderDto orderDto) {
        log.info("*** OrderDto, service; save order *");
        Order order = OrderMappingHelper.mapToOrder(orderDto);
        order.setCreatedAt(Instant.now());
        order.setOrderStatus(OrderStatus.ORDER_CREATED);
        order.setOrderNumber(UUID.randomUUID());


        orderRepository.save(order);
        List<OrderRequestDto> orderRequestDtoList = getOrderRequestDtos(order);
        for (OrderRequestDto requestDto : orderRequestDtoList) {
            orderStatusPublisher.publishOrderEvent(order.getOrderNumber(), requestDto, OrderStatus.ORDER_CREATED);
            log.info(format("sending message to stock-stream Topic::%s", requestDto.toString()));
        }
        return OrderMappingHelper.mapToDto(order);
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        log.info("*** OrderDto, service; update Order *");
        Order order = OrderMappingHelper.mapToOrder(orderDto);
        order.setCreatedAt(Instant.now());
        if (!order.getOrderStatus().equals(OrderStatus.ORDER_CANCELLED)) {
            order.setOrderStatus(OrderStatus.ORDER_CREATED);
        }
        this.orderRepository.save(order);
        List<OrderRequestDto> orderRequestDtoList = getOrderRequestDtos(order);
        for (OrderRequestDto requestDto : orderRequestDtoList) {
            orderStatusPublisher.publishOrderEvent(order.getOrderNumber(), requestDto, order.getOrderStatus());
            log.info(format("sending message to stock-stream Topic::%s", requestDto.toString()));
        }

        return OrderMappingHelper.mapToDto(order);

    }

    @NotNull
    private static List<OrderRequestDto> getOrderRequestDtos(Order order) {
        List<OrderRequestDto> orderRequestDtoList = order.getOrderLineItemsList().stream().map
                (l -> OrderRequestDto.builder()
                        .orderId(l.getOrderLineId())
                        .skuCode(l.getSkuCode())
                        .amount(l.getQuantity())
                        .build()).collect(Collectors.toList());
        return orderRequestDtoList;
    }

    @Override
    public OrderDto update(Integer orderId, OrderDto orderDto) {
        return null;
    }

    @Override
    public void deleteById(Integer orderId) {
        log.info("*** Void, service; delete Order by id *");
        this.orderRepository.delete(OrderMappingHelper
                .mapToOrder(this.findById(orderId)));
    }

    @Override
    public List<OrderDto> findAll() {
        log.info("*** OrderDto List, service; fetch all orders *");
        return this.orderRepository.findAll()
                .stream()
                .map(OrderMappingHelper::mapToDto)
                .map(o -> {

                    if ( o.getUserDto().UserId() == null){
                        o.setUserDto(null);
                    }else {
                        o.setUserDto(this.restTemplate.getForObject("http://USERS/api/v1/auth/user/" + o.getUserDto().UserId(), UserDto.class));

                    }

                    return o;

                })
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public OrderDto findById(Integer orderId) {
        log.info("*** OrderDto, service; fetch order by id *");
        return this.orderRepository.findById(orderId)
                .map(OrderMappingHelper::mapToDto)
                .map(o -> {

                    if ( o.getUserDto().UserId() == null){
                        o.setUserDto(null);
                    }else {
                        o.setUserDto(this.restTemplate.getForObject("http://USERS/api/v1/auth/user/" + o.getUserDto().UserId(), UserDto.class));

                    }

                    return o;

                })
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id: %d not found", orderId)));

    }


}
