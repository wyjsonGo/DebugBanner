package com.wyjson.debug_banner;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;

/**
 * debug show checked mode banner label
 *
 * @author Wyjson
 * @version 2 解耦 String... activities
 * @date 2019-09-21 23:45
 */
public class DebugBanner implements Application.ActivityLifecycleCallbacks {

    private final Banner banner;

    public static DebugBanner.Companion Companion = new DebugBanner.Companion();

    public static final class Companion {
        public void init(Application application, Banner banner) {
            new DebugBanner(application, banner, false);
        }

        public void init(Application application, Banner banner, boolean filterShow, String... activities) {
            new DebugBanner(application, banner, filterShow, activities);
        }

        private Companion() {

        }

    }

    private DebugBanner(Application app, Banner banner, boolean filterShow, String... activities) {
        this.banner = banner;
        app.registerActivityLifecycleCallbacks(this);
        setFilter(filterShow, activities);
    }

    /**
     * @param show
     * @param activities
     */
    public void setFilter(boolean show, String... activities) {
        this.showFlag = show;
        this.activities = activities;
    }

    private String[] activities;
    private boolean showFlag;

    private boolean needShow(Activity activity) {
        if (activities == null) {
            return true;
        }
        for (String a : activities) {
            if (a.equals(activity.getClass().getSimpleName())) {
                return showFlag;
            }
        }
        return !showFlag;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (needShow(activity)) {
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();

            Banner localBanner = activity instanceof BannerView ? ((BannerView) activity).newBanner() : this.banner;

            DebugBannerView debugBannerView = new DebugBannerView(activity, null);
            debugBannerView.updateText(localBanner.getBannerText(), localBanner.getTextColorRes());
            debugBannerView.updateBannerColor(localBanner.getBannerColorRes());
            debugBannerView.setBannerGravity(localBanner.getBannerGravity());
            int bannerSize = activity.getResources().getDimensionPixelOffset(R.dimen.debug_banner_default_size);
            decorView.addView(debugBannerView, new ViewGroup.MarginLayoutParams(bannerSize, bannerSize));
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                debugBannerView.setTranslationY(ScreenUtils.getStatusBarHeight(activity));
            }
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}