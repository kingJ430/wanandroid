package com.zjf.commonlib.bean

class Resource<T> {
    val code: Int
    val msg: String?
    val data: T?

    constructor(data: T? = null, code: Int = SUCCESS_CODE, msg: String? = null) {
        this.code = code
        this.msg = msg
        this.data = data
    }

    fun isSuccess() = (code == SUCCESS_CODE)

    companion object {

        val DEFAULT_ERROR_CODE = -1
        val SUCCESS_CODE = 0

        fun <T> success(data: T?): Resource<T> {
            return Resource(data)
        }

        fun <T> failed(msg: String?, code: Int = DEFAULT_ERROR_CODE): Resource<T> {
            return Resource(null, code, msg)
        }

    }
}