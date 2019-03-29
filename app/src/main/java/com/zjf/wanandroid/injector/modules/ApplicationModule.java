package com.zjf.wanandroid.injector.modules;

import android.app.Application;
import com.zjf.commonlib.injector.scope.AppScope;
import com.zjf.wanandroid.app.ZApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangjianfeng
 */

@Module
public class ApplicationModule {

    protected ZApplication mApplication;

    public ApplicationModule(ZApplication application) {
        mApplication = application;
    }

    @Provides
    @AppScope
    ZApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @AppScope
    Application provideApp() {
        return mApplication;
    }

}
