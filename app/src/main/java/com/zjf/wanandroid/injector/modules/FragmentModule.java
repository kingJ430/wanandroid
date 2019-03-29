package com.zjf.wanandroid.injector.modules;

import com.zjf.commonlib.injector.scope.FragmentScope;
import com.zjf.wanandroid.MainFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhangjianfeng
 */
@Module
public abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();

}
