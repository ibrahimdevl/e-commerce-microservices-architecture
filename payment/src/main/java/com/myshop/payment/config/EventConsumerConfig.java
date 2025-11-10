package com.myshop.payment.config;

import com.myshop.commonDtos.events.StockEvent;
import com.myshop.commonDtos.events.ValidatedOrder;
import com.myshop.commonDtos.events.enums.OrderStatus;
import com.myshop.commonDtos.events.enums.StockAvailabilityStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class EventConsumerConfig {

    //private final OrderStatusUpdateHandler handler;

    @Bean
    public Consumer<ValidatedOrder> orderEventConsumer(){
        //listen payment-event-topic
        //will check payment status
        //if payment status completed -> complete the order
        //if payment status failed -> cancel the order
        return (validatedOrder)->

                    log.info("Order status updated to {}",validatedOrder.getEventId());


    }
}
