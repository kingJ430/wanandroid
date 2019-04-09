package com.zjf.main.bean

/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/8
 */

data class BannerBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)