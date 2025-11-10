package com.myshop.commonDtos.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
@Data
public class OrderRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String skuCode;
    private Integer amount;
    private Integer orderId;
}
