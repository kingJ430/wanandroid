package com.zjf.main.vm

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.zjf.commonlib.base.vm.BaseViewModel
import com.zjf.commonlib.bean.Resource
import com.zjf.main.app.MainApp
import com.zjf.main.bean.ArtList
import com.zjf.main.bean.BannerBean
import javax.inject.Inject

/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/4
 */
class MainViewModel @Inject constructor(repository: MainRepository?) : BaseViewModel<MainRepository>(repository) {


    var pageNo = 0

    var bannerLiveData = MediatorLiveData<Resource<List<BannerBean>>>()
    var artLiveData = MediatorLiveData<Resource<ArtList>>()

    fun requestBanner() {
        bannerLiveData.addSource(mRepository.requestBanner()) {
            bannerLiveData.value = it
        }
    }

    fun requestArtList() {
        pageNo = 0
        artLiveData.addSource(mRepository.requestArtList(pageNo)) {
            pageNo ++
            artLiveData.value = it
        }
    }

    fun loadMoreArtList() {
        artLiveData.addSource(mRepository.requestArtList(pageNo)) {
            pageNo ++
            artLiveData.value = it
        }
    }


}