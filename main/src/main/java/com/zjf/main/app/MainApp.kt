package com.zjf.main.app

import android.app.Activity
import android.util.Log
import com.zjf.commonlib.app.CoreApplication
import com.zjf.commonlib.app.IApplicationlike
import com.zjf.commonlib.injector.utils.DispatchingCoreAndroidInjector
import com.zjf.main.injector.components.DaggerMainAppComponent
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/8
 */
class MainApp : IApplicationlike {

    @Inject
    lateinit var mActivityInjector: DispatchingCoreAndroidInjector<Activity>


    companion object {
        var instance by Delegates.notNull<MainApp>()
    }

    val mMainComponent by lazy {
        DaggerMainAppComponent.builder()
            .baseComponent(CoreApplication.instance.getDaggerDelegate().component)
            .build()
    }

    override fun onCreate() {
        Log.e("MainApp","main模块初始化")
        instance = this
        setAppGraph()
    }

    private fun setAppGraph() {
        mMainComponent.inject(this)
//        CoreApplication.instance.getDaggerDelegate().addActivityInjector(mActivityInjector)
    }

    override fun onStop() {
    }
}