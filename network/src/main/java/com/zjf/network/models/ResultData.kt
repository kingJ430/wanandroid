package com.zjf.network.models

/**
 * @Desc
 * @Author zjf
 * @Date 2019/3/22
 */
class ResultData<T> {

    var errorCode = -1
    var errorMsg = ""
    var data : T ?= null
}