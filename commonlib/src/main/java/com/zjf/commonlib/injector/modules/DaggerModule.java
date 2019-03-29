package com.zjf.commonlib.injector.modules;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by zhangjianfeng
 * Dagger Library Module
 */
@Module
public class DaggerModule {
    private final Application mApplication;

    public DaggerModule(Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return this.mApplication;
    }

}
