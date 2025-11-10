package com.myshop.commonDtos.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Builder
@ToString
@Data
public class ValidatedOrderDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer totalAmount;
    private Integer orderId;
}
