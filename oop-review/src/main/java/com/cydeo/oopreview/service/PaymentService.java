package com.cydeo.oopreview.service;


import com.cydeo.oopreview.exception.InvalidPaymentStrategyException;
import com.cydeo.oopreview.model.payment.AbstractPaymentResponse;
import com.cydeo.oopreview.model.payment.AuthRequest;

public interface PaymentService {
    AbstractPaymentResponse auth(AuthRequest authRequest) throws InvalidPaymentStrategyException;

    AbstractPaymentResponse auth3D(AuthRequest auth3DRequest) throws InvalidPaymentStrategyException;

}
