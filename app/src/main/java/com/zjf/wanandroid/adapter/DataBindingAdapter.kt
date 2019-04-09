package com.zjf.wanandroid.adapter

import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/8
 */
@BindingMethods(
    BindingMethod(
        type = BottomNavigationView::class,
        attribute = "app:onNavigationItemSelected",
        method = "setOnNavigationItemSelectedListener"
    )
)
class DataBindingAdapter
