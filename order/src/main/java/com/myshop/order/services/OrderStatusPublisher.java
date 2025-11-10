package com.myshop.order.services;

import com.myshop.commonDtos.dto.OrderRequestDto;
import com.myshop.commonDtos.dto.PaymentRequestDto;
import com.myshop.commonDtos.dto.ValidatedOrderDto;
import com.myshop.commonDtos.events.OrderEvent;
import com.myshop.commonDtos.events.PaymentEvent;
import com.myshop.commonDtos.events.ValidatedOrder;
import com.myshop.commonDtos.events.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderStatusPublisher {


    private final Sinks.Many<OrderEvent> orderSinks;

    private final Sinks.Many<ValidatedOrder> validatedOrderSinks;



    public void publishOrderEvent(UUID id , OrderRequestDto orderRequestDto, OrderStatus orderStatus){
        OrderEvent orderEvent=new OrderEvent(id ,orderRequestDto,orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }

    public void publishPaymentEvent(UUID id , ValidatedOrderDto validatedOrderDto, OrderStatus orderStatus){
        ValidatedOrder validatedOrder=new ValidatedOrder(id ,validatedOrderDto,orderStatus);
        validatedOrderSinks.tryEmitNext(validatedOrder);
    }
}
