package com.zjf.commonlib.base;

import android.content.Context;
import androidx.fragment.app.Fragment;
import com.zjf.commonlib.injector.utils.CoreAndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import javax.inject.Inject;

/**
 * user: zhangjianfeng
 * date: 2/27/2019
 * version:
 */

public class DaggerBaseFragment extends Fragment implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Override
    public void onAttach(Context context) {
        CoreAndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }
}
