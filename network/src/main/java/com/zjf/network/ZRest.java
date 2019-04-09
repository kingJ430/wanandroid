package com.zjf.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zjf.network.models.INetExternalParams;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Desc 公用网络类 初始化okhttpclient和Retrofit
 * @Author zjf
 * @Date 2019/3/15
 */
public class ZRest {

    private final Retrofit mRetrofit;
    private final OkHttpClient mOkHttpClient;
    private static ZRest mpmRest;

    public static ZRest getInstance() {
        if (mpmRest == null) {
            mpmRest = new ZRest();
        }
        return mpmRest;
    }

    private ZRest() {
        final OkHttpClient.Builder builder;
        builder = generateDefaultOkHttpBuilder();
        final INetExternalParams networkParams = ZNetWork.get().externalParams();

        List<Interceptor> interceptorList = ZNetWork.get().interceptors();
        for (Interceptor interceptor : interceptorList) {
            builder.addInterceptor(interceptor);
        }

        List<Interceptor> networkInterceptorsList = ZNetWork.get().networkInterceptors();
        for (Interceptor interceptor : networkInterceptorsList) {
            builder.addNetworkInterceptor(interceptor);
        }
        builder.proxy(getProxy(networkParams));
        mOkHttpClient = builder.build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(networkParams.baseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxTransformErrorCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(mOkHttpClient)
                .validateEagerly(true)
                .build();
    }



    private Proxy getProxy(INetExternalParams iNetExternalParams) {
        if (!iNetExternalParams.isRelease()) {
            //非release，由proxySelector来选择
            return null;
        }
        return Proxy.NO_PROXY;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

//    public OkHttpClient getDownLoadHttpClient(OnDownloadListener onDownloadListener) {
//        OkHttpClient.Builder  builder = generateDefaultOkHttpBuilder();
//        //创建一个OkHttpClient，并添加网络拦截器
//        OkHttpClient client = builder
//                .addNetworkInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Response response = chain.proceed(chain.request());
//                        //这里将ResponseBody包装成我们的ProgressResponseBody
//                        return response.newBuilder()
//                                .body(new ProgressResponseBody(response.body(),onDownloadListener))
//                                .build();
//                    }
//                })
//                .build();
//        return client;
//    }

    @SuppressWarnings("all")
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    private static final int CONNECT_TIMEOUT = 50;
    private static final int READ_TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 60;

    private static Cache getCache() {
        //缓存文件夹
        File cacheFile = new File(ZNetWork.get().mApplicationContext.getExternalCacheDir().toString(), "mpm/reponse");
        //缓存大小为200M
        int cacheSize = 100 * 1024 * 1024;
        //创建缓存对象
        return new Cache(cacheFile, cacheSize);
    }

    private static OkHttpClient.Builder generateDefaultOkHttpBuilder() {
        return new OkHttpClient.Builder()
//                .cache(getCache())
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
    }
}
