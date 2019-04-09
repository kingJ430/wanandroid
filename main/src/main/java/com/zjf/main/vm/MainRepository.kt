package com.zjf.main.vm

import androidx.lifecycle.MutableLiveData
import com.zjf.commonlib.base.vm.BaseRepository
import com.zjf.commonlib.bean.Resource
import com.zjf.main.bean.ArtList
import com.zjf.main.bean.BannerBean
import com.zjf.main.remote.MainService
import com.zjf.network.models.RestError
import com.zjf.network.subscriber.ConvertObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.function.Consumer
import javax.inject.Inject

/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/8
 */
class MainRepository @Inject constructor(mainService: MainService) : BaseRepository() {

    private val mMainService = mainService

    fun requestBanner() : MutableLiveData<Resource<List<BannerBean>>>{
        val liveData = MutableLiveData<Resource<List<BannerBean>>>()
        mMainService.banner()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ConvertObserver<List<BannerBean>>() {
                override fun onFailed(pRestError: RestError?) {
                    pRestError?.let {
                        liveData.value =  Resource.failed<List<BannerBean>>(it.msg,it.code)
                    }

                }

                override fun onSuccess(t: List<BannerBean>?) {
                    liveData.value = Resource.success(t)
                }

            })
        return liveData
    }

    fun requestArtList(pageNo:Int) : MutableLiveData<Resource<ArtList>> {
        val liveData = MutableLiveData<Resource<ArtList>>()
        mMainService.artList(pageNo)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ConvertObserver<ArtList>() {
                override fun onFailed(pRestError: RestError?) {
                    pRestError?.let {
                        liveData.value =  Resource.failed<ArtList>(it.msg,it.code)
                    }

                }

                override fun onSuccess(t:ArtList?) {
                    liveData.value = Resource.success(t)
                }

            })
        return liveData
    }


}