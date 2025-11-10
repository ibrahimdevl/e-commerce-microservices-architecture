package com.myshop.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myshop.commonDtos.events.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private Integer paymentId;
    @JsonProperty("order")
    private OrderDto orderDto;
    private String paymentIntentId;
    private String description;
    private PaymentStatus paymentStatus;

}
