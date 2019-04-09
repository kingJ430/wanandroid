package com.zjf.wanandroid.app

import android.app.Activity
import androidx.annotation.NonNull
import com.zjf.commonlib.BuildConfig
import com.zjf.commonlib.app.CoreApplication
import com.zjf.commonlib.injector.utils.DispatchingCoreAndroidInjector
import com.zjf.network.ZNetWork
import com.zjf.network.models.INetExternalParams
import com.zjf.wanandroid.injector.components.AppComponent
import com.zjf.wanandroid.injector.components.DaggerAppComponent
import com.zjf.wanandroid.injector.modules.ApplicationModule
import com.zjf.wanandroid.manager.CommunicateManager
import com.zjf.wanandroid.manager.SchemeManager
import com.zjf.wanandroid.utils.Constants
import okhttp3.Interceptor
import java.util.ArrayList
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
        initNetWork()
        CommunicateManager.init()
        SchemeManager.init()
        setAppGraph()
    }

    private fun initNetWork() {
        val interceptors = ArrayList<Interceptor>()
        val netWorkInterceptors = ArrayList<Interceptor>()
        ZNetWork.init(
            ZNetWork.Builder(this)
                .externalParams(object : INetExternalParams {
                    override fun isRelease() = !BuildConfig.DEBUG

                    @NonNull
                    override fun baseUrl(): String {
                        return Constants.BASEURL
                    }
                })
                .interceptors(interceptors)
                .networkInterceptors(netWorkInterceptors)
                .build()
        )
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