package com.zjf.commonlib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.zjf.commonlib.R
import com.zjf.commonlib.app.CoreApplication
import com.zjf.commonlib.base.helper.DialogHelperImpl
import com.zjf.commonlib.base.helper.UIHelperImpl
import com.zjf.network.models.RestError
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.common_activity_base.*

/**
 * @Desc activity基类
 * @Author zjf
 * @Date 2019/3/22
 */
abstract class BaseActivity : DaggerBaseAppCompatActivity() {

    protected lateinit var mToolBar : Toolbar
    private var mUIHelper: UIHelperImpl ?= null
    private var mDialogHelper: DialogHelperImpl ?= null
    private lateinit var mContainerLayout: FrameLayout
    private lateinit var mContentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBaseView()
        generateDefaultHelper()
        initToolbar()
        initView()
        initData()
    }

    open fun initData() {

    }


    private fun generateDefaultHelper() {
        if (this.mUIHelper == null) {
            this.mUIHelper = UIHelperImpl(mContainerLayout, this, mContentView)
        }

        if (this.mDialogHelper == null) {
            this.mDialogHelper = DialogHelperImpl(this)
        }
    }



    fun initBaseView() {
        setContentView(R.layout.common_activity_base)
        mContentView = LayoutInflater.from(this).inflate(getContentLayoutId(), null, false)
        content_layout.addView(mContentView,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        mContainerLayout = content_layout
        mToolBar = toolbar
    }

    abstract fun getContentLayoutId() : Int

    open fun initToolbar() {

    }

    open fun initView() {

    }

    fun setToolbarTitle(resouceId : Int) {
        mToolBar?.setTitle(resouceId)
    }

    fun setToolbarTitle(resouce : String) {
        mToolBar?.title = resouce
    }

    fun showContent() {
        this.mUIHelper?.showContent()
    }

    fun showInPageProgressView() {
        this.mUIHelper?.showInPageProgressView()
    }


    fun showErrorView(@Nullable pErrorMes: String, @DrawableRes pErrorIconRes: Int) {
        this.mUIHelper?.showErrorView(pErrorMes, pErrorIconRes)
    }

    fun showErrorView(pViewGroupParent: View?, pErrorMes: String, @DrawableRes pErrorIconRes: Int) {
        if (null != pViewGroupParent) {
            this.mUIHelper?.showErrorView(pViewGroupParent, pErrorMes, pErrorIconRes)
        }
    }

    fun showErrorView(pExceptionView: View) {
        this.mUIHelper?.showErrorView(pExceptionView)
    }

    fun showErrorView(pRestError: RestError, pIsUseNewUI: Boolean) {
        this.mUIHelper?.showErrorView(pRestError, pIsUseNewUI)
    }

    fun showErrorView(@LayoutRes pExceptionLayoutId: Int, pErrorMes: String, @DrawableRes pErrorIconRes: Int) {
        this.mUIHelper?.showErrorView(pExceptionLayoutId, pErrorMes, pErrorIconRes)
    }

    fun showLoadingDialog()
    {
        this.showLoadingDialog(true, R.string.common_default_loading_message)
    }

    fun showLoadingDialog(@StringRes msgId: Int) {
        this.showLoadingDialog(true, msgId)
    }

    fun showLockableLoadingDialog() {
        this.showLoadingDialog(false,R.string.common_default_loading_message)
    }

    fun showLockableLoadingDialog(@StringRes msgId: Int) {
        this.showLoadingDialog(false, msgId)
    }

    fun showLoadingDialog(isCancelable: Boolean, @StringRes msgId: Int) {
        this.mDialogHelper?.showLoadingDialog(isCancelable, msgId)
    }

    fun dismissLoadingDialog() {
        this.mDialogHelper?.dismissLoadingDialog()
    }

    fun getToolbar(): Toolbar {
        return mToolBar
    }

    override fun getActivityInjector(): HasActivityInjector {
        return (application as CoreApplication).activityInjector()
    }


}