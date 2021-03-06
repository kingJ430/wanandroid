package com.zjf.commonlib.injector.utils;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import androidx.fragment.app.Fragment;
import com.zjf.commonlib.base.DaggerBaseAppCompatActivity;
import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * user: zhangjianfeng
 * date: 11/10/2017
 * version: 7.3
 */

public class CoreAndroidInjection {

    private static final String TAG = "dagger.android";

    /**
     * Injects {@code activity} if an associated {@link AndroidInjector} implementation can be found,
     * otherwise throws an {@link IllegalArgumentException}.
     *
     * @throws RuntimeException if the {@link Application} doesn't implement {@link
     *     HasActivityInjector}.
     */
    public static void inject(DaggerBaseAppCompatActivity activity) {
        checkNotNull(activity, "activity");
        if(activity.getActivityInjector() != null) {

            AndroidInjector<Activity> activityInjector =
                    activity.getActivityInjector().activityInjector();
            checkNotNull(
                    activityInjector,
                    "%s.activityInjector() returned null",
                    activity.getClass().getCanonicalName());

            try {
                activityInjector.inject(activity);
            } catch (Exception exception) {

            }
        }
    }

//    public static void inject(DaggerBaseService service) {
//        checkNotNull(service, "service");
//        if (service.getServiceInjector() != null) {
//            AndroidInjector<Service> serviceInjector =
//                    service.getServiceInjector().serviceInjector();
//            checkNotNull(
//                    serviceInjector,
//                    "%s.serviceInjector() returned null",
//                    service.getClass().getCanonicalName());
//            try {
//                serviceInjector.inject(service);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * Injects {@code fragment} if an associated {@link AndroidInjector} implementation
     * can be found, otherwise throws an {@link IllegalArgumentException}.
     *
     * <p>Uses the following algorithm to find the appropriate {@code AndroidInjector<Fragment>} to
     * use to inject {@code fragment}:
     *
     * <ol>
     *   <li>Walks the parent-fragment hierarchy to find the a fragment that implements {@link
     *       HasSupportFragmentInjector}, and if none do
     *   <li>Uses the {@code fragment}'s {@link Fragment#getActivity() activity} if it implements
     *       {@link HasSupportFragmentInjector}, and if not
     *   <li>Uses the {@link Application} if it implements {@link
     *       HasSupportFragmentInjector}.
     * </ol>
     *
     * If none of them implement {@link HasSupportFragmentInjector}, a {@link
     * IllegalArgumentException} is thrown.
     *
     * @throws IllegalArgumentException if no parent fragment, activity, or application implements
     *     {@link HasSupportFragmentInjector}.
     */
    public static void inject(Fragment fragment) {
        checkNotNull(fragment, "fragment");
        HasSupportFragmentInjector hasSupportFragmentInjector = findHasFragmentInjector(fragment);
        Log.d(
                TAG,
                String.format(
                        "An com.taobao.meipingmi.injector for %s was found in %s",
                        fragment.getClass().getCanonicalName(),
                        hasSupportFragmentInjector.getClass().getCanonicalName()));

        AndroidInjector<Fragment> fragmentInjector =
                hasSupportFragmentInjector.supportFragmentInjector();
//        checkNotNull(
//                fragmentInjector,
//                "%s.supportFragmentInjector() returned null",
//                hasSupportFragmentInjector.getClass().getCanonicalName());

        try {
            fragmentInjector.inject(fragment);
        } catch (Exception e) {

        }
    }

    private static HasSupportFragmentInjector findHasFragmentInjector(Fragment fragment) {
        Fragment parentFragment = fragment;
        while ((parentFragment = parentFragment.getParentFragment()) != null) {
            if (parentFragment instanceof HasSupportFragmentInjector) {
                return (HasSupportFragmentInjector) parentFragment;
            }
        }
        Activity activity = fragment.getActivity();
        if (activity instanceof HasSupportFragmentInjector) {
            return (HasSupportFragmentInjector) activity;
        }
        throw new IllegalArgumentException(
                String.format("No com.taobao.meipingmi.injector was found for %s", fragment.getClass().getCanonicalName()));
    }
}
