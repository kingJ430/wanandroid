package com.zjf.network

import android.annotation.SuppressLint
import android.content.Context
import com.zjf.network.models.INetExternalParams
import okhttp3.Interceptor
import java.net.NetworkInterface

/**
 * @Desc 网络公共类
 * @Author zjf
 * @Date 2019/3/22
 */
class ZNetwork {

    private lateinit var context: Context
    private lateinit var iNetExternalParams: INetExternalParams
    private var networkInterceptors: List<Interceptor>? = null
    private var interceptors: List<Interceptor>? = null


    companion object {
        private var zNetwork : ZNetwork ?= null

        private fun checkoutNotNull(any: Any) {
            if (any == null) {
                throw IllegalArgumentException("call not be null")
            }
        }

        fun get() = zNetwork

        fun init(zNetwork: ZNetwork) {
            this.zNetwork = zNetwork
        }
    }

    private constructor(
        context: Context, iNetExternalParams: INetExternalParams, networkInterceptor: List<Interceptor>?,
        interceptors: List<Interceptor>?) {

    }

    fun networkInterceptors() = networkInterceptors

    fun interceptors() = interceptors

    fun externalParams() = iNetExternalParams

    class Builder {
        private lateinit var context: Context
        private lateinit var iNetExternalParams: INetExternalParams
        private var networkInterceptors: List<Interceptor>? = null
        private var interceptors: List<Interceptor>? = null

        fun externalParams(params: INetExternalParams): Builder {
            iNetExternalParams = params
            return this
        }

        fun networkInterceptors(networkInterceptor: List<Interceptor>): Builder {
            this.networkInterceptors = networkInterceptors
            return this
        }

        fun interceptors(interceptors: List<Interceptor>): Builder {
            this.interceptors = interceptors
            return this
        }

        fun build(): ZNetwork {
            checkoutNotNull(context)
            checkoutNotNull(iNetExternalParams)

            networkInterceptors ?: listOf()
            interceptors ?: listOf()
            return ZNetwork(context,iNetExternalParams,networkInterceptors,interceptors)

        }
    }


}