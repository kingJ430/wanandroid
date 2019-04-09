package com.zjf.main.injector.modules;

import com.communicate.module.library.service.Communicate;
import com.zjf.commonlib.injector.scope.AppScope;
import com.zjf.main.remote.MainService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zhangjianfeng
 */
@Module
public class MainApiModule {


    @Provides
    @AppScope
    Communicate provideCommunicate() {
        return new Communicate();
    }

    @Provides
    @AppScope
    MainService provideMainService(Retrofit retrofit) {
        return retrofit.create(MainService.class);
    }



}
