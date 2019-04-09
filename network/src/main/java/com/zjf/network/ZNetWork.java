package com.zjf.network;

import android.content.Context;
import com.zjf.network.models.INetExternalParams;
import okhttp3.Interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc 公用网络类
 * @Author zjf
 * @Date 2019/3/15
 */
public class ZNetWork {

    private static ZNetWork sNetwork;
    private final List<Interceptor> networkInterceptors;
    private final List<Interceptor> interceptors;
    private final INetExternalParams mExternalParams;
    public final Context mApplicationContext;

    private ZNetWork(Context context, INetExternalParams params,
                     List<Interceptor> networkInterceptors, List<Interceptor> interceptors) {
        this.mApplicationContext = context;
        this.mExternalParams = params;
        this.networkInterceptors = networkInterceptors;
        this.interceptors = interceptors;
    }

    public static final class Builder {
        private Context context;
        private INetExternalParams networkParams;
        private List<Interceptor> networkInterceptors;
        private List<Interceptor> interceptors;

        public Builder(Context context) {
            this.context = context.getApplicationContext();
        }

        public Builder externalParams(INetExternalParams params) {
            this.networkParams = params;
            return this;
        }

        public Builder networkInterceptors(List<Interceptor> networkInterceptors) {
            this.networkInterceptors = networkInterceptors;
            return this;
        }

        public Builder interceptors(List<Interceptor> interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public ZNetWork build() {
            checkNotNull(context, "context");
            checkNotNull(networkParams, "INetExternalParams");

            if (interceptors == null) {
                interceptors = new ArrayList<>();
            }

            if (networkInterceptors == null) {
                networkInterceptors = new ArrayList<>();
            }
            return new ZNetWork(context, networkParams,networkInterceptors, interceptors);
        }

    }


    public static void init(ZNetWork mpmNetwork) {
        if (mpmNetwork == null) {
            throw new RuntimeException("Please using XKNetwork.Builder(context).build() to init XKNetwork");
        }
        sNetwork = mpmNetwork;
    }

    private static void checkNotNull(Object object, String err) {
        if (object == null) {
            throw new IllegalArgumentException(err + " can not be null !");
        }
    }

    public static ZNetWork get() {
        if (sNetwork == null) {
            throw new RuntimeException("Please using XKNetwork.init() in Application first");
        }
        return sNetwork;
    }

    public List<Interceptor> networkInterceptors() {
        return networkInterceptors;
    }

    public List<Interceptor> interceptors() {
        return interceptors;
    }

    public INetExternalParams externalParams() {
        return mExternalParams;
    }

}
