package com.zjf.wanandroid.injector.modules;

import com.communicate.module.library.service.Communicate;
import com.zjf.commonlib.injector.scope.AppScope;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zhangjianfeng
 */
@Module
public class ApiModule {


    @Provides
    @AppScope
    Communicate provideCommunicate() {
        return new Communicate();
    }



}
