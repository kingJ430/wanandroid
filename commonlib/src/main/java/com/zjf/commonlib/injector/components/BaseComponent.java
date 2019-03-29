package com.zjf.commonlib.injector.components;


import com.zjf.commonlib.injector.modules.ApiModule;
import com.zjf.commonlib.injector.modules.DaggerModule;
import com.zjf.commonlib.injector.utils.DaggerDelegate;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import retrofit2.Retrofit;

import javax.inject.Singleton;

/**
 * Created by zhangjianfeng
 */
@Singleton
@Component(
        modules = {AndroidSupportInjectionModule.class, DaggerModule.class, ApiModule.class})
public interface BaseComponent {

    void inject(DaggerDelegate daggerDelegate);

    Retrofit getRetrofit();
}
