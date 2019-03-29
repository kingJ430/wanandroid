package com.zjf.commonlib.injector.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import com.zjf.commonlib.injector.components.BaseComponent;
import com.zjf.commonlib.injector.components.DaggerBaseComponent;
import com.zjf.commonlib.injector.modules.DaggerModule;
import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;

import javax.inject.Inject;


public class DaggerDelegate implements HasActivityInjector, HasServiceInjector {

    private BaseComponent mComponent;
    private final Application mApplication;

    @Inject
    DispatchingCoreAndroidInjector<Activity> mActivityInjector;

    @Inject
    DispatchingCoreAndroidInjector<Service> mServiceInjector;

    public DaggerDelegate(Application application) {
        mApplication = application;
    }

    public void onCreate() {

        //顶级依赖注入
        mComponent = DaggerBaseComponent.builder()
                .daggerModule(new DaggerModule(mApplication))
                .build();
        mComponent.inject(this);

    }


    public BaseComponent getComponent() {
        return mComponent;
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }

    public void addActivityInjector(DispatchingCoreAndroidInjector<Activity> activityInjector) {
        if(mActivityInjector != null && activityInjector != null) {
            mActivityInjector.addAllMap(activityInjector.getInjectorFactories());
        }
    }

    public void addServiceInjector(DispatchingCoreAndroidInjector<Service> serviceInjector) {
        if (mServiceInjector != null && serviceInjector != null) {
            mServiceInjector.addAllMap(serviceInjector.getInjectorFactories());
        }
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return mServiceInjector;
    }
}
