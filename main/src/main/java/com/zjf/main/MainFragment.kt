package com.zjf.main

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.module.common.router.compiler.RouterNavigation
import com.ms.banner.holder.BannerViewHolder
import com.ms.banner.holder.HolderCreator
import com.ms.banner.listener.OnBannerClickListener
import com.zjf.commonlib.base.BaseFragment
import com.zjf.commonlib.extension.getViewModel
import com.zjf.main.app.MainApp
import com.zjf.main.bean.BannerBean
import com.zjf.main.factory.ViewModelFactory
import com.zjf.main.viewholder.CustomViewHolder
import com.zjf.main.vm.MainViewModel
import kotlinx.android.synthetic.main.main_fragment_layout.*
import java.text.FieldPosition
import javax.inject.Inject

/**
 * @Desc
 * @Author zjf
 * @Date 2019/3/29
 */
class MainFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: MainViewModel

//    lateinit var viewModel : MainViewModel
//    val viewModel: MainViewModel by lazy {
//       MainViewModel()
//    }

    override fun getContentLayoutId() = R.layout.main_fragment_layout

    override fun initView(view: View) {
        MainApp.instance.mMainComponent.inject(this)
        //简单使用
        banner.setAutoPlay(true)
            .setDelayTime(3000)

    }
    private fun jumpWebview(bannerBean: BannerBean?) {
        RouterNavigation.navigation()
            .navigate(this.activity,"home/webview")
            .appendQueryParameter("url",bannerBean?.url)
            .appendQueryParameter("title",bannerBean?.title)
            .start()
    }

    override fun initData() {
//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.bannerLiveData.observe(this, Observer {

            initBanner( it.data)
        })
        viewModel.requestBanner()
    }

    private fun initBanner(data: List<BannerBean>?) {
        data?.let {
            banner.setPages(data,
                HolderCreator<BannerViewHolder<*>> { CustomViewHolder() })
                .setOnBannerClickListener(OnBannerClickListener { position ->
                    jumpWebview(data[position])
                }).start()
        }
    }



}