package com.zjf.wanandroid.vm

import android.app.Application
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/4
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    val onNavigationItemSelected = MutableLiveData<MenuItem>()


    fun onNavigationClick(@NonNull item: MenuItem): Boolean {
        if (item.itemId == onNavigationItemSelected.value?.itemId) {
            return true
        }
        onNavigationItemSelected.value = item
        return true
    }


}