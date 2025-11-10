package com.myshop.payment.controllers;

import com.myshop.payment.dto.CreatePayment;
import com.myshop.payment.dto.CreatePaymentResponse;
import com.myshop.payment.dto.DtoCollectionResponse;
import com.myshop.payment.dto.PaymentDto;
import com.myshop.payment.services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@AllArgsConstructor
@Slf4j
public class StripeApi {

private final PaymentService paymentService;

    @PostMapping("/create-payment-intent" )
    @ResponseBody
    public CreatePaymentResponse createPaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException {


        log.info("*** Create Payment Intent ***");
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount((long) (createPayment.getAmount() * 100L)) //TODO: get this from createPayment :  the order amount
                        .setCurrency(createPayment.getCurrency())
                        .setDescription(createPayment.getDescription())
                        // In the latest version of the API, specifying the `automatic_payment_methods` parameter is optional because Stripe enables its functionality by default.
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        // Create a PaymentIntent with the order amount and currency
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        paymentService.save(createPayment,paymentIntent.getId());

        return new CreatePaymentResponse(paymentIntent.getClientSecret());

    }

    @GetMapping("/payments" )
    public ResponseEntity<DtoCollectionResponse<PaymentDto>> findAll() {
        log.info("*** ProductDto List, controller; fetch all products *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.paymentService.findAll()));
    }


}





