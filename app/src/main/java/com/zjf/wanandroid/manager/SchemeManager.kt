package com.zjf.wanandroid.manager

import com.module.common.router.compiler.SchemeRouter



/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/9
 */
object SchemeManager {

    private val mMouduleName = arrayOf("Home")

    private val mSchemes = arrayOf("scheme://")

    fun init() {
        SchemeRouter.setup(mMouduleName, mSchemes)
    }
}