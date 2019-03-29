package com.zjf.wanandroid;

import com.zjf.commonlib.base.BaseFragment;
import com.zjf.wanandroid.app.ZApplication;

import javax.inject.Inject;

/**
 * @Desc
 * @Author zjf
 * @Date 2019/3/29
 */
public class MainFragment extends BaseFragment {

    @Inject
    ZApplication zApplication;

    @Override
    public int getContentLayoutId() {
        return 0;
    }
}
