package com.zjf.commonlib.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/8
 */
fun <T : ViewModel> Fragment.getViewModel(c: Class<T>): T{
    return ViewModelProviders.of(this).get(c)
}