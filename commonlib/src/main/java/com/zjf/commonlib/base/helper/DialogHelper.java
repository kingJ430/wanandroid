package com.zjf.commonlib.base.helper;


import androidx.annotation.StringRes;

/**
 * @author yuefeng
 * @version 3.5.1
 * @desc
 * @date 16/6/2
 *
 */
public interface DialogHelper {
	void showLoadingDialog();

	void dismissLoadingDialog();

	void showLockableLoadingDialog(@StringRes int msgId);

	void showLockableLoadingDialog();

	void showLoadingDialog(boolean isCancelable, @StringRes int msgId);

	void showLoadingDialog(@StringRes int msgId);

	boolean isLoadingDialogShowing();

}
