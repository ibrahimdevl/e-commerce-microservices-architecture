package com.myshop.order.controllers;

import com.myshop.order.dto.DtoCollectionResponse;
import com.myshop.order.dto.OrderDto;
import com.myshop.order.services.Impl.OrderServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService ;

    @PostMapping
    public ResponseEntity<OrderDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDto orderDto) {
        log.info("*** OrderDto, controller; save order *");
        return ResponseEntity.ok(this.orderService.save(orderDto));
    }

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<OrderDto>> findAll() {
        log.info("*** OrderDto List, controller; fetch all orders *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.orderService.findAll()));
    }

    @GetMapping("/{OrderId}")
    public ResponseEntity<OrderDto> findById(
            @PathVariable("OrderId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String OrderId) {
        log.info("*** OrderDto, controller; fetch order by id *");
        return ResponseEntity.ok(this.orderService.findById(Integer.parseInt(OrderId)));
    }

    @PutMapping
    public ResponseEntity<OrderDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDto orderDto) {
        log.info("*** OrderDto, controller; update Order *");
        return ResponseEntity.ok(this.orderService.update(orderDto));
    }

    @DeleteMapping("/{OrderId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("OrderId") final String orderId) {
        log.info("*** Boolean, resource; delete order by id *");
        this.orderService.deleteById(Integer.parseInt(orderId));
        return ResponseEntity.ok(true);
    }
}
