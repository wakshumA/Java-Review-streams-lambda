package com.cydeo.oopreview.posclient;

import com.cydeo.oopreview.helper.CollectionHelper;
import com.cydeo.oopreview.model.pos.PosClientRequest;
import com.cydeo.oopreview.model.pos.PosClientResponse;

import java.util.UUID;

import static com.cydeo.oopreview.constant.StaticConstants.ORDER_ID_LIST;

public abstract class AbstractPosClient {

    public UUID generateOrderId(){
        UUID uuid = UUID.randomUUID();
        if (checkOrderIdExists(uuid.toString())){
            return generateOrderId();
        }else {
            return uuid;
        }
    }

    private boolean checkOrderIdExists(String generatedOrderId){
        if (CollectionHelper.isNotNullOrEmpty(ORDER_ID_LIST)){
            return ORDER_ID_LIST.stream().findAny().toString().equals(generatedOrderId);
        }
        return false;
    }

    //calls any banks auth api
    public abstract PosClientResponse auth(PosClientRequest posClientRequest);

    //calls any banks auth3D api
    public abstract PosClientResponse auth3D(PosClientRequest posClientRequest);


}
