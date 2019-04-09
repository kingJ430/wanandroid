package com.zjf.main.vm

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.zjf.commonlib.base.vm.BaseViewModel
import com.zjf.commonlib.bean.Resource
import com.zjf.main.app.MainApp
import com.zjf.main.bean.BannerBean
import javax.inject.Inject

/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/4
 */
class MainViewModel @Inject constructor(repository: MainRepository?) : BaseViewModel<MainRepository>(repository) {



    var bannerLiveData = MediatorLiveData<Resource<List<BannerBean>>>()

    fun requestBanner() {
        bannerLiveData.addSource(mRepository.requestBanner()) {
            bannerLiveData.value = it
        }
    }


}