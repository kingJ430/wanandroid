package com.zjf.commonlib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.zjf.commonlib.R
import com.zjf.commonlib.base.helper.DialogHelper
import com.zjf.commonlib.base.helper.DialogHelperImpl
import com.zjf.commonlib.base.helper.UIHelper
import com.zjf.commonlib.base.helper.UIHelperImpl
import kotlinx.android.synthetic.main.common_fragment_base.*

/**
 * @Desc
 * @Author zjf
 * @Date 2019/3/29
 */
abstract class BaseFragment : DaggerBaseFragment() {
    protected lateinit var mContainerLayout : FrameLayout
    private lateinit var mContentView: View
    private lateinit var mToolbar: Toolbar
    private var mUIHelper: UIHelper ?= null
    private var mDialogHelper: DialogHelper ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = initBaseView(inflater,container)
        initBaseToolBar()
        generateDefaultHelper()
        return view
    }

    fun onCreateSubView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData()
    }

    open fun initData() {

    }

    open  fun initView(view: View) {

    }

    private fun initBaseView(inflater: LayoutInflater, container: ViewGroup?) : View {
        var view =inflater.inflate(R.layout.common_fragment_base,container,false)
        mContainerLayout = view.findViewById(R.id.fragment_content)
        mContentView = inflater.inflate(getContentLayoutId(),null,false)
        mContainerLayout.addView(mContentView,-1,-1)
        return view
    }



    abstract fun getContentLayoutId() : Int


    private fun initBaseToolBar() {
        if (this.activity is BaseActivity<*>) {
            this.mToolbar = (this.activity as BaseActivity<*>).getToolbar()
            this.setHasOptionsMenu(true)
        }

    }

    private fun generateDefaultHelper() {
        if (this.mUIHelper == null) {
            this.mUIHelper = UIHelperImpl(this.mContainerLayout, this.activity, this.mContentView)
        }

        if (this.mDialogHelper == null) {
            this.mDialogHelper = DialogHelperImpl(this.activity)
        }

    }

    fun showLoadingDialog() {
        this.showLoadingDialog(true, R.string.common_default_loading_message)
    }

    fun showLoadingDialog(@StringRes msgId: Int) {
        this.showLoadingDialog(true, msgId)
    }

    fun showLockableLoadingDialog() {
        this.showLoadingDialog(false, R.string.common_default_loading_message)
    }

    fun showLockableLoadingDialog(@StringRes msgId: Int) {
        this.showLoadingDialog(false, msgId)
    }

    fun showLoadingDialog(isCancelable: Boolean, @StringRes msgId: Int) {
        if (this.activity != null) {
            this.mDialogHelper?.showLoadingDialog(isCancelable, msgId)
        }
    }

    fun dismissLoadingDialog() {
        this.mDialogHelper?.dismissLoadingDialog()
    }

    fun showInPageProgressView() {
        this.mUIHelper?.showInPageProgressView()
    }

}