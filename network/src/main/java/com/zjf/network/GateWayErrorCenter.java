package com.zjf.network;

import com.google.gson.JsonObject;
import com.zjf.network.exception.ZNumberFormatException;
import com.zjf.network.exception.ZBizException;


/**
 * Gateway Error Center
 */
public class GateWayErrorCenter {

    public static void throwExceptionWhenNotSuccess(JsonObject jsonObject) {
        if (jsonObject.has(ItemTypeAdapterFactory.CODE) && !"0".equals(jsonObject.get(ItemTypeAdapterFactory.CODE).getAsString())) {
            String msg = "";
            String errorCode = "";
            if (jsonObject.has(ItemTypeAdapterFactory.MESSAGE)) {
                msg = jsonObject.get(ItemTypeAdapterFactory.MESSAGE).getAsString();
            }
            if (jsonObject.has(ItemTypeAdapterFactory.CODE)) {
                errorCode = jsonObject.get(ItemTypeAdapterFactory.CODE).getAsString();
            }
            try {
                int code = Integer.parseInt(errorCode);
                throw new ZBizException(msg, code);
            } catch (NumberFormatException e) {
                throw new ZNumberFormatException(msg, errorCode);
            }
        }
    }

}
