package com.zjf.main.injector.components;

import com.zjf.commonlib.injector.components.BaseComponent;
import com.zjf.commonlib.injector.scope.AppScope;
import com.zjf.main.MainFragment;
import com.zjf.main.app.MainApp;
import com.zjf.main.injector.modules.MainActivityModule;
import com.zjf.main.injector.modules.MainApiModule;
import com.zjf.main.injector.modules.MainFragmentModule;
import com.zjf.main.injector.modules.VMModule;
import com.zjf.main.vm.MainViewModel;
import dagger.Component;

/**
 * Created by zhangjianfeng
 */
@AppScope
@Component(
        dependencies = BaseComponent.class,
        modules = { MainApiModule.class,
                MainActivityModule.class,
                MainFragmentModule.class
//                VMModule.class
        })
public interface MainAppComponent {

    void inject(MainApp mainApp);
    void inject(MainViewModel mainViewModel);
    void inject(MainFragment mainFragment);



}
