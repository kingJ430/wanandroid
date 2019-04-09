package com.zjf.wanandroid.webview

import androidx.databinding.ViewDataBinding
import com.module.common.annoation.Router
import com.zjf.commonlib.base.BaseActivity
import com.zjf.wanandroid.R
import com.zjf.wanandroid.databinding.HomeWebviewBinding
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import android.graphics.Bitmap
import android.text.TextUtils
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.just.agentweb.AgentWebSettingsImpl
import com.just.agentweb.IAgentWebSettings
import kotlinx.android.synthetic.main.home_webview.*
import java.util.*
import java.util.logging.Logger


/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/8
 */
@Router(path = arrayOf("home/webview"),paramName = arrayOf("url","title"),paramAlias = arrayOf("url","title"),paramType = arrayOf("s","s") )
class H5Activity : BaseActivity<HomeWebviewBinding>() {

    private var mUrl: String?= null

    private var mTitle: String?= null
    private lateinit var mAgentWeb: AgentWeb
    private val urlList: LinkedList<String> = LinkedList()
    private val articleTitleList: LinkedList<String> = LinkedList()

    override fun getContentLayoutId() = com.zjf.wanandroid.R.layout.home_webview

    override fun initToolbar() {
        super.initToolbar()
    }

    override fun initView() {
        super.initView()
        urlList.add(intent.getStringExtra("url"))
        mTitle = intent.getStringExtra("title")
        setToolbarTitle(mTitle ?: "")
    }

    override fun initData() {
        super.initData()
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(linear_content as LinearLayout, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setAgentWebWebSettings(MyAgentWebSettingsImpl())
            .setWebChromeClient(object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    articleTitleList.add(title?:"")
                    if (TextUtils.isEmpty(mTitle)) setToolbarTitle( title!!)
                }
            })
            .interceptUnkownUrl()
            .setWebViewClient(object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    url?.apply { urlList.add(url) }
                    super.onPageStarted(view, url, favicon)
                }
            })
            .createAgentWeb()
            .ready()
            .go(urlList.last)
    }

    override fun onNavigationClick() {
        if (mAgentWeb.back()) {
            urlList.removeLast()
            true
        } else {
            finish()
        }
    }

    //页面显示适配屏幕
    class MyAgentWebSettingsImpl : AgentWebSettingsImpl() {
        override fun toSetting(webView: WebView?): IAgentWebSettings<*> {
            val iAgentWebSettings = super.toSetting(webView)
            webView?.settings?.run {
                builtInZoomControls = true
                displayZoomControls = false
                setSupportZoom(true)
                loadWithOverviewMode = true
                useWideViewPort = true
            }
            return iAgentWebSettings
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            urlList.removeLast()
            true
        } else {
            finish()
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onResume() {
        mTitle?.let {
            setToolbarTitle(it)
        }
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()

    }

    override fun onDestroy() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }


}