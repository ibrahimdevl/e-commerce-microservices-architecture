package com.myshop.order.config;

import com.myshop.commonDtos.dto.OrderRequestDto;
import com.myshop.commonDtos.dto.ValidatedOrderDto;
import com.myshop.commonDtos.events.enums.OrderStatus;
import com.myshop.order.entities.Order;
import com.myshop.order.repositories.OrderRepository;
import com.myshop.order.services.OrderStatusPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class OrderStatusUpdateHandler {


    private final OrderRepository orderRepository;
    private final OrderStatusPublisher publisher;

    @Transactional
    public void updateOrder(UUID id, Consumer<Order> consumer) {
        orderRepository.findByOrderNumber(id).ifPresent(consumer.andThen(this::updateOrder));
    }

    private void updateOrder(Order order) {
        System.out.println("Order status updated to " + order.getOrderStatus());

        if (order.getOrderStatus().equals(OrderStatus.PENDING_PAYMENT)) {

            ValidatedOrderDto validatedOrderDto = ValidatedOrderDto.builder()
                    .orderId(order.getOrderId())
                    .userId(78963214)
                    .totalAmount(49)
                    .build();



            publisher.publishPaymentEvent(order.getOrderNumber(), validatedOrderDto, order.getOrderStatus());


        }
    }
}

