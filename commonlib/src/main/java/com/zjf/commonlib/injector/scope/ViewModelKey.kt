package com.zjf.commonlib.injector.scope

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/8
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class  ViewModelKey(val value: KClass<out ViewModel>)