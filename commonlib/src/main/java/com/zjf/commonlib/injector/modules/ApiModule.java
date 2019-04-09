package com.zjf.commonlib.injector.modules;

import com.zjf.network.ZRest;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import javax.inject.Singleton;

/**
 * Created by zhangjianfeng on 2017/6/16.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return ZRest.getInstance().getRetrofit();
    }



}
