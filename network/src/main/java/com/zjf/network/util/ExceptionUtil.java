package com.zjf.network.util;


import android.util.Log;
import com.zjf.network.exception.*;
import com.zjf.network.models.RestError;


public class ExceptionUtil {
    private static final String TAG = "MPMException";
    public static final int SUCCESS = 200;
    public static final int EXCEPTION_NETWORK = 500;
    //数据异常(脏数据)
    public static final int EXCEPTION_DIRTY_DATA = EXCEPTION_NETWORK + 1;
    //业务异常
    public static final int EXCEPTION_BUSINESS = EXCEPTION_DIRTY_DATA + 1;

    public static RestError generateRestError(Throwable throwable) {
        if (null == throwable) return new RestError("未知错误!");

        String message = throwable.getMessage();
        int code = 0;
        final int type;

       if (throwable instanceof NoNetworkException
                || throwable instanceof NetworkTimeOutException
                || throwable instanceof NetworkErrorException
                || throwable instanceof ServerBusyException) {
            type = EXCEPTION_NETWORK;
        }  else if (throwable instanceof DirtyDataException) {
            type = EXCEPTION_DIRTY_DATA;
            code = ((DirtyDataException) throwable).getCode();
        } else if (throwable instanceof ZBizException) {
            type = SUCCESS;
            code = ((ZBizException) throwable).getCode();
        }  else if (throwable instanceof ConversionException) {
            type = EXCEPTION_BUSINESS;
        } else {
            type = EXCEPTION_BUSINESS;
//            message = "未知错误";
        }

        if (message == null) {
            message = "";
        }
        Log.e(TAG, message);

        RestError restError = new RestError(message);
        restError.setCode(code);
        restError.setErrorType(type);
        return restError;
    }
}
