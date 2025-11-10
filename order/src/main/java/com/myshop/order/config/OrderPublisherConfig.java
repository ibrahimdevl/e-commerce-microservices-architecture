package com.myshop.order.config;

import com.myshop.commonDtos.events.OrderEvent;
import com.myshop.commonDtos.events.PaymentEvent;
import com.myshop.commonDtos.events.ValidatedOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class OrderPublisherConfig {

    @Bean
    public Sinks.Many<OrderEvent> orderSinks(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Sinks.Many<ValidatedOrder> paymentSinks(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<OrderEvent>> orderSupplier(Sinks.Many<OrderEvent> sinks){
        return sinks::asFlux;
    }

    @Bean
    public Supplier<Flux<ValidatedOrder>> orderSupplierToPayment(Sinks.Many<ValidatedOrder> sinks){
        return sinks::asFlux;
    }
}
