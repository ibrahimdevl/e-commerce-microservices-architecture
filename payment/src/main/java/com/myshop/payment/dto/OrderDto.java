package com.myshop.payment.dto;

import com.myshop.commonDtos.events.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer orderId;

    private UUID orderNumber;

    private OrderStatus orderStatus;



}
