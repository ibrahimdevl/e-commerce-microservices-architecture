package com.myshop.payment.services;

import com.myshop.payment.dto.CreatePayment;
import com.myshop.payment.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> findAll();
    PaymentDto findById(final Integer paymentId);
    PaymentDto save(final CreatePayment paymentDto, final String paymentIntentId);
    PaymentDto update(final PaymentDto paymentDto);
    void handlePayment(final String paymentIntendId , final String paymentStatus);
    void deleteById(final Integer paymentId);
}
