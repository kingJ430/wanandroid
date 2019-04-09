package com.zjf.main.injector.modules;

import com.zjf.commonlib.injector.scope.ActivityScope;
import com.zjf.main.DemoActivity;
import com.zjf.main.MainFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhangjianfeng
 */
@Module
public abstract class MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract DemoActivity contributeDemoActivity();




}
