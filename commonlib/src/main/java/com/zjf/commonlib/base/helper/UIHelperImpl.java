package com.zjf.commonlib.base.helper;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import com.zjf.commonlib.R;
import com.zjf.network.models.RestError;
import com.zjf.network.util.ExceptionUtil;

/**
 */
public class UIHelperImpl implements UIHelper {
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private FrameLayout mContainerLayout;
//    private ExceptionDispose mExceptionDispose;
    private View mContentView;
    private AnimationDrawable mLoadingAnimation;


    public UIHelperImpl(FrameLayout pContainerLayout, Activity pActivity, View pContentView) {
        mContainerLayout = pContainerLayout;
        mLayoutInflater = pActivity.getLayoutInflater();
        mActivity = pActivity;
        this.mContentView = pContentView;
    }

    @Override
    public void showErrorView(RestError pRestError, boolean pIsUseNewUI) {
        if (null == pRestError) return;
        mContainerLayout.removeAllViews();
        mContainerLayout.addView(getErrorViewLayoutRes(pRestError, pIsUseNewUI));
    }

    private View getErrorViewLayoutRes(RestError pRestError, boolean pIsUseNewUI) {
        switch (pRestError.getErrorType()) {
            case ExceptionUtil.EXCEPTION_DIRTY_DATA:
            case ExceptionUtil.SUCCESS:
            case ExceptionUtil.EXCEPTION_BUSINESS:

                return generateDirtyDataExceptionView(pRestError, pIsUseNewUI);
            default:
                return generateNetworkExceptionVieW(pRestError, pIsUseNewUI);
        }
    }

    private View generateDirtyDataExceptionView(RestError pRestError, boolean pIsUseNewUI) {
        View _view = mLayoutInflater.inflate(R.layout.common_exception_dirty_data_layout, mContainerLayout, false);
        if (pIsUseNewUI) {
            ((ImageView) _view.findViewById(R.id.iv_exception_icon)).setImageResource(R.mipmap.common_icon_error_normal);
        }
        ((TextView) _view.findViewById(R.id.tv_exception_tip)).setText(pRestError.getMsg());
        return _view;
    }


    private View generateNetworkExceptionVieW(RestError pRestError, boolean pIsUseNewUI) {
        View _view = mLayoutInflater.inflate(R.layout.common_network_error_page, mContainerLayout, false);
        if (pIsUseNewUI) {
            ((ImageView) _view.findViewById(R.id.iv_exception_icon)).setImageResource(R.mipmap.common_icon_error_network);
        }
        ((TextView) _view.findViewById(R.id.tv_exception_tip)).setText(pRestError.getMsg());
        return _view;
    }


    @Override
    public void showErrorView(@Nullable String pErrorMes, @DrawableRes int pErrorIconRes) {
        mContainerLayout.removeAllViews();
        mLayoutInflater.inflate(R.layout.common_network_error_page, mContainerLayout);
        ((ImageView) mContainerLayout.findViewById(R.id.iv_exception_icon)).setImageResource(pErrorIconRes);
        if (!TextUtils.isEmpty(pErrorMes)) {
            ((TextView) mContainerLayout.findViewById(R.id.tv_exception_tip)).setText(pErrorMes);
        }

    }

    @Override
    public void showErrorView(RestError pRestError) {
        if (null == pRestError) return;
        mContainerLayout.removeAllViews();
        mContainerLayout.addView(getErrorViewLayoutRes(pRestError));

    }


    @Override
    public void showInPageProgressView() {
        if (mContainerLayout.getChildCount() > 0) {
            mContainerLayout.removeAllViews();
        }
        mLayoutInflater.inflate(R.layout.common_loading_view, mContainerLayout);
    }

    @Override
    public void commonExceptionDispose(RestError pRestError) {
//        if (null == pRestError) return;
//        if (null == mExceptionDispose) {
//            this.mExceptionDispose = new ExceptionDispose(mActivity);
//        }
//        this.mExceptionDispose.dispose(pRestError);

    }

    @Override
    public void showContent() {
        if (mContainerLayout.getChildCount() > 0) {
            mContainerLayout.removeAllViews();
        }
        mContainerLayout.addView(mContentView);


    }


    @Override
    public void showErrorView(View pViewGroupParent, String pErrorMes, @DrawableRes int pErrorIconRes) {
        if (null == pViewGroupParent) return;
        mContainerLayout.removeAllViews();
        mContainerLayout.addView(pViewGroupParent);
        ImageView _imageView = (ImageView) mContainerLayout.findViewById(R.id.iv_exception_icon);
        if (null != _imageView) {
            _imageView.setImageResource(pErrorIconRes);
        }
        TextView _textView = (TextView) mContainerLayout.findViewById(R.id.tv_exception_tip);
        if (null != _textView) {
            _textView.setText(pErrorMes);
        }

    }

    @Override
    public void showErrorView(@LayoutRes int pExceptionLayoutId, String pErrorMes, @DrawableRes int pErrorIconRes) {
        showErrorView(mLayoutInflater.inflate(pExceptionLayoutId, mContainerLayout, false), pErrorMes, pErrorIconRes);
    }

    @Override
    public void showErrorView(View pExceptionView) {
        if (null == pExceptionView) return;
        mContainerLayout.removeAllViews();
        mContainerLayout.addView(pExceptionView);
    }


    private View getErrorViewLayoutRes(RestError pRestError) {
        switch (pRestError.getErrorType()) {
            case ExceptionUtil.EXCEPTION_DIRTY_DATA:
            case ExceptionUtil.SUCCESS:
            case ExceptionUtil.EXCEPTION_BUSINESS:

                return generateDirtyDataExceptionView(pRestError);
            default:
                return generateNetworkExceptionVieW(pRestError);
        }
    }

    private View generateNetworkExceptionVieW(RestError pRestError) {
        View _view = mLayoutInflater.inflate(R.layout.common_network_error_page, mContainerLayout, false);
        ((TextView) _view.findViewById(R.id.tv_exception_tip)).setText(pRestError.getMsg());
        return _view;
    }

    private View generateDirtyDataExceptionView(RestError pRestError) {
        View _view = mLayoutInflater.inflate(R.layout.common_exception_dirty_data_layout, mContainerLayout, false);
        ((TextView) _view.findViewById(R.id.tv_exception_tip)).setText(pRestError.getMsg());
        return _view;
    }


    @Override
    public void setActivityBackground(String pBGUrl, View contentView) {
    }


    @Override
    public View getSlidingViewInContentView() {
        return null;
    }

    @Override
    public void onActivityDestroy() {
    }


}
