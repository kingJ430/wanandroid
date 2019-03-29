package com.zjf.wanandroid.injector.modules;

import com.zjf.commonlib.injector.scope.ActivityScope;
import com.zjf.wanandroid.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhangjianfeng
 */
@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();




}
