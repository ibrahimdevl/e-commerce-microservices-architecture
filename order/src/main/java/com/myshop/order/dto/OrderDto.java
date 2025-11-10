package com.myshop.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myshop.commonDtos.events.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDto  implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer orderId;

    private UUID orderNumber;

    private OrderStatus orderStatus;

    @JsonProperty("user")
 //   @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDto userDto;

    @JsonProperty("orderLine")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrderLineDto> orderLineItemsList;


}
