package com.zjf.main.remote

import com.communicate.module.library.annotation.RouterPath
import com.zjf.commonlib.base.BaseFragment

/**
 * @Desc
 * @Author zjf
 * @Date 2019/3/29
 */
interface HomeFragmentService {

    @RouterPath(provider = "main/mainFragment")
    fun obtainMainFragment() : BaseFragment?

    @RouterPath(provider = "discovery/discoveryFragment")
    fun obtainDiscoveryFragment() : BaseFragment?

    @RouterPath(provider = "navigation/navigationFragment")
    fun obtainNaviFragment() : BaseFragment?

    @RouterPath(provider = "user/mineFragment")
    fun obtainMineFragment() : BaseFragment?
}