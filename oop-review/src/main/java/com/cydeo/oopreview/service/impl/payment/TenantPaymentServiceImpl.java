package com.cydeo.oopreview.service.impl.payment;

import com.cydeo.oopreview.exception.InvalidPaymentStrategyException;
import com.cydeo.oopreview.model.payment.AbstractPaymentResponse;
import com.cydeo.oopreview.model.payment.AuthRequest;
import com.cydeo.oopreview.model.payment.Payment;
import com.cydeo.oopreview.model.pos.Pos;
import com.cydeo.oopreview.model.pos.PosClientRequest;
import com.cydeo.oopreview.model.pos.PosClientResponse;
import com.cydeo.oopreview.posclient.AbstractPosClient;
import com.cydeo.oopreview.posclient.client.BankAPosClient;
import com.cydeo.oopreview.posclient.client.BankBPosClient;
import com.cydeo.oopreview.posclient.client.BankCPosClient;
import com.cydeo.oopreview.service.PaymentService;
import com.cydeo.oopreview.service.PosSelectionService;
import com.cydeo.oopreview.service.impl.initialization.HybridPosInitializationServiceImpl;
import com.cydeo.oopreview.service.impl.initialization.TenantPosInitializationServiceImpl;
import com.cydeo.oopreview.service.impl.selection.HybridPosSelectionServiceImpl;
import com.cydeo.oopreview.service.impl.selection.TenantPosSelectionServiceImpl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

import static com.cydeo.oopreview.constant.StaticConstants.CYDEO_PAYMENT_LIST;


public class TenantPaymentServiceImpl implements PaymentService {

    private final ResourceBundle resourceBundle;

    public TenantPaymentServiceImpl(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public AbstractPaymentResponse auth(AuthRequest authRequest) throws InvalidPaymentStrategyException {
        AbstractPaymentResponse abstractPaymentResponse = new AbstractPaymentResponse();

        PosSelectionService posSelectionService = decidePaymentPosThatWillBeProcessed(authRequest);

        Pos pos = posSelectionService.decidePaymentPos(authRequest);

        //add comment
        String recipient = "Recipient";

        AbstractPosClient abstractPosClient;

        PosClientRequest posClientRequest = new  PosClientRequest(authRequest.getAmount(),
                pos.getName(),
                recipient);

        abstractPosClient = decidePosClient(pos.getName());

        UUID orderId = abstractPosClient.generateOrderId();
        posClientRequest.setOrderId(orderId.toString());

        PosClientResponse posClientResponse = abstractPosClient.auth(posClientRequest);

        abstractPaymentResponse = doErrorCodeMapping(posClientResponse, resourceBundle);

        abstractPaymentResponse.setPaymentCostAmount(calculateCommissionForTenantMerchant(
                authRequest.getAmount(), pos.getInstallmentCommissionMap().get(authRequest.getInstallment())));

        if (abstractPaymentResponse.getResult() == 1){
            initPaymentRecord(authRequest, orderId);
        }
        return abstractPaymentResponse;
    }


    @Override
    public AbstractPaymentResponse auth3D(AuthRequest auth3DRequest) {
        AbstractPaymentResponse abstractPaymentResponse = new AbstractPaymentResponse();

        PosSelectionService posSelectionService = decidePaymentPosThatWillBeProcessed(auth3DRequest);

        Pos pos = posSelectionService.decidePaymentPos(auth3DRequest);
        String recipient = "Recipient";

        AbstractPosClient abstractPosClient;

        PosClientRequest posClientRequest = new PosClientRequest(auth3DRequest.getAmount(),
                pos.getName(),
                recipient);

        abstractPosClient = decidePosClient(pos.getName());

        UUID orderId = abstractPosClient.generateOrderId();

        posClientRequest.setOrderId(orderId.toString());

        PosClientResponse posClientResponse = abstractPosClient.auth3D(posClientRequest);

        abstractPaymentResponse = doErrorCodeMapping(posClientResponse, resourceBundle);

        if (abstractPaymentResponse.getResult() == 1){
            abstractPaymentResponse.setPaymentCostAmount(calculateCommissionForTenantMerchant(
                    auth3DRequest.getAmount(), pos.getInstallmentCommissionMap().get(auth3DRequest.getInstallment())));

            initPaymentRecord(auth3DRequest, orderId);
        }

        return abstractPaymentResponse;
    }

    public BigDecimal calculateCommissionForTenantMerchant(BigDecimal paymentAmount, Double commissionRate) {
        return paymentAmount.multiply(new BigDecimal(commissionRate))
                .divide(new BigDecimal(100))
                .round(MathContext.DECIMAL32);
    }

    AbstractPaymentResponse doErrorCodeMapping(PosClientResponse posClientResponse, ResourceBundle resourceBundle){

        AbstractPaymentResponse abstractPaymentResponse = new AbstractPaymentResponse();
        abstractPaymentResponse.setResult(posClientResponse.getResult());

        if (posClientResponse.getResult() != 1){
            abstractPaymentResponse.
                    setResultMessage(resourceBundle.getString(
                            resourceBundle.getString("error.code." + posClientResponse.getErrorCde())));

            abstractPaymentResponse.setErrorCde(posClientResponse.getErrorCde());
        }
        return abstractPaymentResponse;
    }

    public void initPaymentRecord(AuthRequest authRequest, UUID orderId){
        Payment payment = new Payment(new Date(), authRequest.getAmount(), orderId, authRequest.getAmount());
        CYDEO_PAYMENT_LIST.add(payment);
    }

    public AbstractPosClient decidePosClient(String posName){
        switch (posName){
            case "BANKA":
                return new BankAPosClient();
            case "BANKB":
                return new BankBPosClient();
            case "BANKC":
                return new BankCPosClient();
            default:
                return null;
        }
    }

    private PosSelectionService decidePaymentPosThatWillBeProcessed(AuthRequest authRequest) {
        if (authRequest.isHybridPayment()) {
            return new HybridPosSelectionServiceImpl(new HybridPosInitializationServiceImpl());
        } else {
            return new TenantPosSelectionServiceImpl(new TenantPosInitializationServiceImpl());
        }
    }
}
