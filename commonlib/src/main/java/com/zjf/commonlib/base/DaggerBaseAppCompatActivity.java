package com.zjf.commonlib.base;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.zjf.commonlib.injector.utils.CoreAndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

import javax.inject.Inject;

/**
 * user: zhangjianfeng
 * date: 12/10/2017
 * version: 7.3
 */
public abstract class DaggerBaseAppCompatActivity extends AppCompatActivity
        implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;
//    @Inject DispatchingCoreAndroidInjector<android.app.Fragment> frameworkFragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CoreAndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

//    @Override
//    public AndroidInjector<android.app.Fragment> fragmentInjector() {
//        return frameworkFragmentInjector;
//    }

    public abstract HasActivityInjector getActivityInjector();
}
