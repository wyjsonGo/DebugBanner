package com.wyjson.debugbannerlibrary;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wyjson.debugbannerlibrary.utils.ScreenUtils;

/**
 * debug show checked mode banner label
 *
 * @author Wyjson
 * @version 1
 * @date 2019-09-21 23:45
 */
public class DebugBanner implements Application.ActivityLifecycleCallbacks {

    private Banner banner;

    public static DebugBanner.Companion Companion = new DebugBanner.Companion();

    public static final class Companion {
        public final void init(Application application, Banner banner) {
            new DebugBanner(application, banner, false, null);
        }

        public final void init(Application application, Banner banner, boolean filterShow, @NonNull Class... activities) {
            new DebugBanner(application, banner, filterShow, activities);
        }

        private Companion() {

        }

    }

    private DebugBanner(Application app, Banner banner, boolean filterShow, @NonNull Class... activities) {
        this.banner = banner;
        app.registerActivityLifecycleCallbacks(this);
        setFilter(filterShow, activities);
    }

    /**
     * @param show
     * @param activities
     */
    public void setFilter(boolean show, @NonNull Class... activities) {
        this.showFlag = show;
        this.activities = activities;
    }

    private Class[] activities;
    private boolean showFlag;

    private boolean needShow(Activity activity) {
        if (activities == null) {
            return true;
        }
        for (Class a : activities) {
            if (a.isInstance(activity)) {
                return showFlag;
            }
        }
        return !showFlag;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        if (needShow(activity)) {

            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();

            Banner localBanner = activity instanceof BannerView ? ((BannerView) activity).newBanner() : this.banner;

            DebugBannerView debugBannerView = new DebugBannerView(activity, null);
            debugBannerView.updateText(localBanner.getBannerText(), localBanner.getTextColorRes());
            debugBannerView.updateBannerColor(localBanner.getBannerColorRes());
            debugBannerView.setBannerGravity(localBanner.getBannerGravity());
            int bannerSize = activity.getResources().getDimensionPixelOffset(R.dimen.banner_default_size);
            decorView.addView(debugBannerView, new ViewGroup.MarginLayoutParams(bannerSize, bannerSize));
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
                debugBannerView.setTranslationY(ScreenUtils.getStatusBarHeight(activity));

        }
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}