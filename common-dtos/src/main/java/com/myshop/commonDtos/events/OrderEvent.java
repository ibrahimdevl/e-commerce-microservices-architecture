package com.myshop.commonDtos.events;

import com.myshop.commonDtos.dto.OrderRequestDto;
import com.myshop.commonDtos.events.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class OrderEvent implements Event , Serializable {
    private static final long serialVersionUID = 1L;
    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private UUID orderNumber;
    private OrderRequestDto orderRequestDto;
    private OrderStatus orderStatus;

    public OrderEvent(UUID orderNumber ,OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        this.orderNumber = orderNumber;
        this.orderRequestDto = orderRequestDto;
        this.orderStatus = orderStatus;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

}
