package com.zjf.commonlib.base.helper;

import android.app.Activity;
import android.app.Dialog;
import androidx.annotation.StringRes;
import com.zjf.commonlib.R;

/**
 * @author yuefeng
 * @version 3.5.1
 * @desc
 * @date 16/6/2
 */
public class DialogHelperImpl implements DialogHelper {
	private Activity mActivity;
	private Dialog mLoadingDialog;

	public DialogHelperImpl(Activity pActivity) {
		mActivity = pActivity;
	}

	@Override
	public void showLoadingDialog() {
//		showLoadingDialog(true, R.string.ddcx_default_loading_message);
	}

	@Override
	public void dismissLoadingDialog() {
		if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
			mLoadingDialog.dismiss();
		}
	}

	@Override
	public void showLockableLoadingDialog(@StringRes int msgId) {
		showLoadingDialog(false, msgId);

	}

	public boolean isLoadingDialogShowing(){
		return mLoadingDialog != null && mLoadingDialog.isShowing();
	}

	@Override
	public void showLockableLoadingDialog() {
//		showLoadingDialog(false, R.string.ddcx_default_loading_message);

	}

	@Override
	public void showLoadingDialog(boolean isCancelable, @StringRes int msgId) {
		if (null == mLoadingDialog) {
//			mLoadingDialog = new XKLoading(mActivity, isCancelable, msgId);
		}
		if (!mLoadingDialog.isShowing()) {
			mLoadingDialog.show();
		}
	}

	@Override
	public void showLoadingDialog(@StringRes int msgId) {
		showLoadingDialog(true, msgId);
	}
}
