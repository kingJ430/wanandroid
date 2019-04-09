package com.zjf.main.injector.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zjf.commonlib.injector.scope.ViewModelKey
import com.zjf.main.factory.ViewModelFactory
import com.zjf.main.vm.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/8
 */
@Module
abstract class VMModule {



    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}