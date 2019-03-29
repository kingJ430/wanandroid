package com.zjf.wanandroid.app

import android.app.Activity
import com.zjf.commonlib.app.CoreApplication
import com.zjf.commonlib.injector.utils.DispatchingCoreAndroidInjector
import com.zjf.wanandroid.injector.components.AppComponent
import com.zjf.wanandroid.injector.components.DaggerAppComponent
import com.zjf.wanandroid.injector.modules.ApplicationModule
import com.zjf.wanandroid.manager.CommunicateManager
import javax.inject.Inject

/**
 * @Desc
 * @Author zjf
 * @Date 2019/3/29
 */
class ZApplication : CoreApplication() {

    private lateinit var mAppComponent : AppComponent

    @Inject
    internal lateinit var mActivityInjector: DispatchingCoreAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        CommunicateManager.init()
        setAppGraph()
    }

    private fun setAppGraph() {
        mAppComponent = DaggerAppComponent.builder()
            .baseComponent(mDaggerDelegate.component)
            .applicationModule(ApplicationModule(this))
            .build()//.inject(this);
        mAppComponent.inject(this)
        mDaggerDelegate.addActivityInjector(mActivityInjector)
    }
}