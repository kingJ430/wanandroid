package com.zjf.network.models

/**
 * @Desc 网络初始化需要的参数
 * @Author zjf
 * @Date 2019/3/22
 */
interface INetExternalParams {

    fun isRelease() : Boolean

    fun baseUrl() : String
}