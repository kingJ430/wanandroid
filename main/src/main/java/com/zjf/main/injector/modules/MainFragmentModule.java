package com.zjf.main.injector.modules;

import com.zjf.commonlib.injector.scope.ActivityScope;
import com.zjf.commonlib.injector.scope.FragmentScope;
import com.zjf.main.MainFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhangjianfeng
 */
@Module
public abstract class MainFragmentModule {

//    @FragmentScope
//    @ContributesAndroidInjector
//    abstract MainFragment contributeMainFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();

}
