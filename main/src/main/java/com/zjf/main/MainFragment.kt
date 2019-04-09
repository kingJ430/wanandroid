package com.zjf.main

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.module.common.router.compiler.RouterNavigation
import com.ms.banner.BannerConfig
import com.ms.banner.holder.BannerViewHolder
import com.ms.banner.holder.HolderCreator
import com.ms.banner.listener.OnBannerClickListener
import com.zjf.commonlib.base.BaseFragment
import com.zjf.main.adapter.HomeAdapter
import com.zjf.main.app.MainApp
import com.zjf.main.bean.BannerBean
import com.zjf.main.viewholder.CustomViewHolder
import com.zjf.main.vm.MainViewModel
import kotlinx.android.synthetic.main.main_banner_item_layout.view.*
import kotlinx.android.synthetic.main.main_home_layout.*
import javax.inject.Inject

/**
 * @Desc
 * @Author zjf
 * @Date 2019/3/29
 */
class MainFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var mAdapter: HomeAdapter

    private var isFirst = true

//    lateinit var viewModel : MainViewModel
//    val viewModel: MainViewModel by lazy {
//       MainViewModel()
//    }

    override fun getContentLayoutId() = R.layout.main_home_layout

    override fun initView(view: View) {
        MainApp.instance.mMainComponent.inject(this)
        recyclerview.adapter = mAdapter
        recyclerview.layoutManager = LinearLayoutManager(this.context,RecyclerView.VERTICAL,false)
        refreshLayout.setOnRefreshListener(object : RefreshListenerAdapter() {

            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                super.onRefresh(refreshLayout)
            }

            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                super.onLoadMore(refreshLayout)
                isFirst = false
                viewModel.loadMoreArtList()
            }
        })

    }

    override fun initData() {
//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.bannerLiveData.observe(this, Observer {
            if (it.isSuccess()) {
                mAdapter.setBannerData(it.data!!)
            } else {
                mAdapter.setBannerData(listOf())
            }
        })
        viewModel.artLiveData.observe(this, Observer {
            if (it.isSuccess()) {
                if (isFirst) {
                    mAdapter.setArtData(it.data!!.datas)
                } else {
                    mAdapter.addArtData(it.data!!.datas)
                }

            }
        })
        viewModel.requestBanner()
        isFirst = true
        viewModel.requestArtList()

    }




}