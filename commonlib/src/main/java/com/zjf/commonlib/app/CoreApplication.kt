package com.zjf.commonlib.app

import android.app.Application
import com.zjf.commonlib.injector.utils.DaggerDelegate
import dagger.android.HasActivityInjector
import kotlin.properties.Delegates

/**
 * @Desc
 * @Author zjf
 * @Date 2019/3/29
 */
open class CoreApplication : Application() {

    protected lateinit var mDaggerDelegate: DaggerDelegate

    companion object {
        var instance : CoreApplication by Delegates.notNull()
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        setCoreGraph()
    }



    fun setCoreGraph() {
        mDaggerDelegate = DaggerDelegate(this)
        mDaggerDelegate.onCreate()

    }

    fun getDaggerDelegate(): DaggerDelegate {
        return mDaggerDelegate
    }

    fun activityInjector(): HasActivityInjector {
        return mDaggerDelegate
    }
}