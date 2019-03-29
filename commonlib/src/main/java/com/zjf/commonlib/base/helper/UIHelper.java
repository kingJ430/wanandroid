package com.zjf.commonlib.base.helper;

import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import com.zjf.network.models.RestError;


public interface UIHelper {

	public void showErrorView(RestError pRestError, boolean pIsUseNewUI);

	void showErrorView(@Nullable String pErrorMes, @DrawableRes int pErrorIconRes);


	/**
	 * @param pErrorMes 异常消息
	 *                  下一版本把异常统一下（消息的行数,现在有显示一行的，也有两行的）
	 *                  这里再加一个参数，DrawableRes
	 */
	void showErrorView(RestError pErrorMes);


	void showInPageProgressView();


	void commonExceptionDispose(RestError pRestError);

	void showContent();

	/**
	 * @param pExceptionView 错误提示的layout
	 * @param pErrorMes      错误提示文字
	 * @param pErrorIconRes  错误提示图片
	 */
	void showErrorView(View pExceptionView, String pErrorMes, @DrawableRes int pErrorIconRes);

	/**
	 * @param pExceptionLayoutId 错误提示的layout id(此layout中的控件id有特殊要求，符合才能显示)
	 * @param pErrorMes          错误提示文字
	 * @param pErrorIconRes      错误提示图片
	 */
	void showErrorView(@LayoutRes int pExceptionLayoutId, String pErrorMes, @DrawableRes int pErrorIconRes);

	/**
	 * @param pExceptionView 错误提示的layout
	 */
	void showErrorView(View pExceptionView);


	void setActivityBackground(String pBGUrl, View contentView);

	//获取content view 中的scrollView
	View getSlidingViewInContentView();

	void  onActivityDestroy();


}
