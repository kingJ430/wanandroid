package com.zjf.wanandroid.injector.components;

import com.zjf.commonlib.injector.components.BaseComponent;
import com.zjf.commonlib.injector.scope.AppScope;
import com.zjf.wanandroid.app.ZApplication;
import com.zjf.wanandroid.injector.modules.ActivityModule;
import com.zjf.wanandroid.injector.modules.ApiModule;
import com.zjf.wanandroid.injector.modules.ApplicationModule;
import com.zjf.wanandroid.injector.modules.FragmentModule;
import dagger.Component;

/**
 * Created by zhangjianfeng
 */
@AppScope
@Component(
        dependencies = BaseComponent.class,
        modules = {ApplicationModule.class, ApiModule.class,
                ActivityModule.class,
                FragmentModule.class
        })
public interface AppComponent  {

    void inject(ZApplication zApplication);



}
