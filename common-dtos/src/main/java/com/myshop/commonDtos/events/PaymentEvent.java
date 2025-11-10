package com.myshop.commonDtos.events;

import com.myshop.commonDtos.dto.PaymentRequestDto;
import com.myshop.commonDtos.events.enums.PaymentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
@NoArgsConstructor
@Data
public class PaymentEvent implements Event, Serializable {
    private static final long serialVersionUID = 1L;
    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private UUID orderNumber;
    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;

    public PaymentEvent(UUID orderNumber, PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
        this.orderNumber = orderNumber;
        this.paymentRequestDto = paymentRequestDto;
        this.paymentStatus = paymentStatus;
    }


    @Override
    public UUID getEventId() {
        return getEventId();
    }

    @Override
    public Date getDate() {
        return getDate();
    }
}
