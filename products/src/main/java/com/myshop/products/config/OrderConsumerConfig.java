package com.myshop.products.config;

import com.myshop.commonDtos.events.OrderEvent;
import com.myshop.commonDtos.events.enums.OrderStatus;
import com.myshop.commonDtos.events.StockEvent;
import com.myshop.products.services.Impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
@Slf4j
public class OrderConsumerConfig {

    @Autowired   ProductServiceImpl productService;

    @Bean
    public Function<Flux<OrderEvent>,Flux<StockEvent>> stockProcessor() {

        //log.info("Processing new order event: {}", this.processStock(new OrderEvent()));
        return orderEventFlux -> orderEventFlux.flatMap(this::processStock);
    }


    public Mono<StockEvent> processStock(OrderEvent orderEvent) {
        // get product sku
        // check the stock availability
        // if stock sufficient -> order completed and deduct amount from DB
        // if stock not sufficient -> cancel order event and update the amount in DB
        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())) {
            Mono<StockEvent> stockEventMono = Mono.fromSupplier(() -> this.productService.newOrderEvent(orderEvent));
            return stockEventMono;

        }
        else{
            log.info("*** StockEvent, service; StockEvent *");
            return Mono.fromRunnable(()->this.productService.cancelOrderEvent(orderEvent));

        }
    }

}
