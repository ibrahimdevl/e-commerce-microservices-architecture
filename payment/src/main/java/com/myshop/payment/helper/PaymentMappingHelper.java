package com.myshop.payment.helper;

import com.myshop.payment.dto.CreatePayment;
import com.myshop.payment.dto.OrderDto;
import com.myshop.payment.dto.PaymentDto;
import com.myshop.payment.entities.Payment;

public interface PaymentMappingHelper {

    static Payment maptoPayment(final CreatePayment paymentDto) {
            return Payment.builder()
                    .

    orderId(paymentDto.getOrderId())
            .

    description(paymentDto.getDescription())
            .


    build();
}

static PaymentDto mapToDto(final Payment payment) {
        return PaymentDto.builder()
                .paymentId(payment.getPaymentId())
                .orderDto(OrderDto.builder()
                        .orderId(payment.getOrderId())
                        .build())
                .paymentStatus(payment.getPaymentStatus())
                .paymentIntentId(payment.getPaymentIntentId())
                .description(payment.getDescription())
                .build();
}

}
