package com.zjf.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zjf.main.bean.BannerBean
import javax.inject.Inject
import android.view.LayoutInflater
import android.view.View
import com.module.common.router.compiler.RouterNavigation
import com.ms.banner.BannerConfig
import com.ms.banner.holder.BannerViewHolder
import com.ms.banner.holder.HolderCreator
import com.ms.banner.listener.OnBannerClickListener
import com.zjf.main.R
import com.zjf.main.bean.ArtList
import com.zjf.main.bean.Data
import com.zjf.main.viewholder.CustomViewHolder
import kotlinx.android.synthetic.main.main_art_item_layout.view.*
import kotlinx.android.synthetic.main.main_banner_item_layout.view.*


/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/9
 */
class HomeAdapter @Inject constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val BANNER_TYPE = 1
        const val ART_TYPE = 2
    }

    private var bannerList = listOf<BannerBean>()
    private var artList = mutableListOf<Data>()

    fun setBannerData(list: List<BannerBean>) {
        bannerList = list
        notifyDataSetChanged()
    }

    fun setArtData(list: MutableList<Data>) {
        artList = list
        notifyDataSetChanged()
    }

    fun addArtData(list: MutableList<Data>) {
        if (list.isNullOrEmpty()) {
            return
        }
        artList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (!bannerList.isNullOrEmpty() && position == 0) {
            return BANNER_TYPE
        }
        return ART_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == BANNER_TYPE) {
            val item = LayoutInflater.from(parent.context).inflate(R.layout.main_banner_item_layout, parent, false)
            return BannerViewHolder(item)
        } else {
            val item = LayoutInflater.from(parent.context).inflate(R.layout.main_art_item_layout, parent, false)
            return ArtViewHolder(item)
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        if (!bannerList.isNullOrEmpty()) {
            count ++
        }
        if (!artList.isNullOrEmpty()) {
            count += artList.size
        }
        return count
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BannerViewHolder) {
            holder.bind()
        } else {
            if (!bannerList.isNullOrEmpty()) {
                var index = position - 1
                (holder as ArtViewHolder).bind(artList[index])
            }
        }
    }

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind() {
            //简单使用
            itemView.banner.setAutoPlay(true)
                .setDelayTime(3000)
            initBanner(bannerList)
        }

        private fun initBanner(data: List<BannerBean>?) {
            data?.let {
                var titleList = arrayListOf<String>()
                it.forEach {
                    titleList.add(it.title)
                }
                itemView.banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                itemView.banner.setBannerTitles(titleList)
                itemView.banner.setPages(data,
                    HolderCreator<com.ms.banner.holder.BannerViewHolder<*>> { CustomViewHolder() })
                    .setOnBannerClickListener(OnBannerClickListener { position ->
                        jumpWebview(data[position])
                    })
                    .start()
            }
        }

            private fun jumpWebview(bannerBean: BannerBean?) {
                RouterNavigation.navigation()
                    .navigate(itemView.context,"home/webview")
                    .appendQueryParameter("url",bannerBean?.url)
                    .appendQueryParameter("title",bannerBean?.title)
                    .start()
            }
    }

    inner class ArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(data: Data) {
            itemView.tv_title.text = data.title
            itemView.tv_time.text = data.niceDate
            itemView.tv_author.text = data.author
            itemView.setOnClickListener {
                jumpWebview(data)
            }
        }

        private fun jumpWebview(data: Data) {
            RouterNavigation.navigation()
                .navigate(itemView.context,"home/webview")
                .appendQueryParameter("url",data.link)
                .appendQueryParameter("title",data.title)
                .start()
        }
    }

}