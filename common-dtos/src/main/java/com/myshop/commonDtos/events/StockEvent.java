package com.myshop.commonDtos.events;

import com.myshop.commonDtos.dto.StockRequestDto;
import com.myshop.commonDtos.events.enums.StockAvailabilityStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
@NoArgsConstructor
@Data
public class StockEvent implements Event , Serializable {
    private static final long serialVersionUID = 1L;
    private UUID eventId=UUID.randomUUID();
    private Date eventDate=new Date();
    private UUID orderNumber;
    private StockRequestDto stockRequestDto;
    private StockAvailabilityStatus stockavailabilityStatus;

    public StockEvent(UUID orderNumber ,StockRequestDto stockRequestDto, StockAvailabilityStatus stockavailabilityStatus) {
        this.orderNumber = orderNumber;
        this.stockRequestDto = stockRequestDto;
        this.stockavailabilityStatus = stockavailabilityStatus;
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
