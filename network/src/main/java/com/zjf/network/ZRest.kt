package com.zjf.network

import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Desc 初始化retrofit和okhttp
 * @Author zjf
 * @Date 2019/3/22
 */
class ZRest {

    private var okHttpClient: OkHttpClient? = null
    private var mRetrofit: Retrofit? = null

    companion object {
        private var zRest: ZRest? = null
        private const val CONNECT_TIMEOUT = 15
        private const val READ_TIMEOUT = 30
        private const val WRITE_TIMEOUT = 30

        fun getInstance() : ZRest? {
            if (zRest == null) {
                zRest = ZRest()
            }
            return zRest
        }
    }

    private constructor() {
        val networkParams = ZNetwork.get()?.externalParams()
        okHttpClient = with(generateDefaultOkHttpBuilder()) {
            ZNetwork.get()?.networkInterceptors()?.let {
                it.forEach {
                    addNetworkInterceptor(it)
                }
            }
            ZNetwork.get()?.interceptors()?.let {
                it.forEach {
                    addInterceptor(it)
                }
            }
            build()
        }
        val gson = GsonBuilder()
            .setLenient()
            .registerTypeAdapterFactory(ItemTypeAdapterFactory())
            .create()

        mRetrofit =  with(Retrofit.Builder())  {
            baseUrl(networkParams?.baseUrl())
            addConverterFactory(GsonConverterFactory.create(gson))
            addCallAdapterFactory(RxTransformErrorCallAdapterFactory.createWithScheduler(Schedulers.io()))
            client(okHttpClient)
            validateEagerly(true)
            build()
        }
    }

    fun getOkHttpClient(): OkHttpClient? {
        return okHttpClient
    }

    fun getRetrofit(): Retrofit? {
        return mRetrofit
    }

    fun <T> create(service: Class<T>): T? {
        return mRetrofit?.create(service)
    }

    private fun generateDefaultOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            //                .cache(getCache())
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
    }
}