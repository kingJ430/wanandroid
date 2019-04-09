package com.zjf.wanandroid

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.communicate.module.library.service.Communicate
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zjf.commonlib.base.BaseActivity
import com.zjf.commonlib.base.BaseFragment
import com.zjf.main.remote.HomeFragmentService
import com.zjf.wanandroid.databinding.ActivityMainBinding
import com.zjf.wanandroid.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var mCurrentFragmentType = 0

    companion object {
        const val TYPE_FRAGMENT_MAIN = 1
        const val TYPE_FRAGMENT_SECOND = 2
        const val TYPE_FRAGMENT_THIRD = 3
        const val TYPE_FRAGMENT_FOUR = 4

    }

    @Inject
    lateinit var mCommunicate : Communicate

    private var currentIndex: Int = 0
    private var mainFragment: BaseFragment ?= null
    private var discoveryFragment: BaseFragment ?= null
    private var naviFragment: BaseFragment ?= null
    private var mineFragment: BaseFragment ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getContentLayoutId() = R.layout.activity_main

    override fun initToolbar() {
        mToolBar?.navigationIcon = null
        setToolbarTitle("主页")
    }

    override fun initView() {
//        bnve.setOnNavigationItemSelectedListener {
//            when(it.itemId) {
//                R.id.navigation_home -> {
//                    setToolbarTitle("主页")
//                    switchFragment(TYPE_FRAGMENT_MAIN)
//                }
//                R.id.navigation_tree -> {
//                    setToolbarTitle("体系")
//                    switchFragment(TYPE_FRAGMENT_SECOND)
//                }
//                R.id.navigation_navi -> {
//                    setToolbarTitle("导航")
//                    switchFragment(TYPE_FRAGMENT_THIRD)
//                }
//                R.id.navigation_todo -> {
//                    setToolbarTitle("我")
//                    switchFragment(TYPE_FRAGMENT_FOUR)
//                }
//            }
//            return@setOnNavigationItemSelectedListener true
//        }
    }

//    private fun setupBottomNavMenu(navController: NavController) {
//        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
//        bottomNav?.setupWithNavController(navController)
//    }

    override fun initData() {
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        viewModel.onNavigationItemSelected.observe(this,
            Observer<MenuItem> {
                when(it.itemId) {
                    R.id.navigation_home -> {
                        setToolbarTitle("主页")
                        switchFragment(TYPE_FRAGMENT_MAIN)
                    }
                    R.id.navigation_tree -> {
                        setToolbarTitle("体系")
                        switchFragment(TYPE_FRAGMENT_SECOND)
                    }
                    R.id.navigation_navi -> {
                        setToolbarTitle("导航")
                        switchFragment(TYPE_FRAGMENT_THIRD)
                    }
                    R.id.navigation_todo -> {
                        setToolbarTitle("我")
                        switchFragment(TYPE_FRAGMENT_FOUR)
                    }
                }
            })
        switchFragment(TYPE_FRAGMENT_MAIN)
    }



    private fun switchFragment(toFragmentType: Int) {
        try {
            val transaction = supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            var toFragment = findFragmentByType(toFragmentType)
            val fromFragment = findFragmentByType(mCurrentFragmentType)
            if (fromFragment != null) {
                transaction.hide(fromFragment)
            }
            if (toFragment == null) {
                toFragment = createFragment(toFragmentType)
            }

            toFragment?.let {
                if (toFragment.isAdded) {
                    transaction.show(toFragment)
                } else {
                    transaction.add(
                        R.id.content_fragment, toFragment,
                        toFragment::class.java.simpleName
                    )
                }
            }

            transaction.commitNowAllowingStateLoss()
            mCurrentFragmentType = toFragmentType
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun findFragmentByType(type: Int): BaseFragment? {
        mainFragment?.let {
            when (type) {
                TYPE_FRAGMENT_MAIN -> return mainFragment
                TYPE_FRAGMENT_SECOND -> return discoveryFragment
                TYPE_FRAGMENT_THIRD -> return naviFragment
                TYPE_FRAGMENT_FOUR -> return mineFragment
                else -> return MainFragment()
            }
        }

        return null
    }

    private fun createFragment(type: Int): BaseFragment? {

        when (type) {
            TYPE_FRAGMENT_MAIN ->  {
                mainFragment = mCommunicate.create(HomeFragmentService::class.java).obtainMainFragment()
                return mainFragment
            }
            TYPE_FRAGMENT_SECOND -> {
                discoveryFragment = mCommunicate.create(HomeFragmentService::class.java).obtainDiscoveryFragment()
                return discoveryFragment
            }
            TYPE_FRAGMENT_THIRD -> {
                naviFragment = mCommunicate.create(HomeFragmentService::class.java).obtainNaviFragment()
                return naviFragment
            }
            TYPE_FRAGMENT_FOUR -> {
                mineFragment = mCommunicate.create(HomeFragmentService::class.java).obtainMineFragment()
                return mineFragment
            }
        }
        return MainFragment()
    }



}
