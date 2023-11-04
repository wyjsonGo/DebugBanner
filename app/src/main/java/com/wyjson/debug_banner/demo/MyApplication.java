package com.wyjson.debug_banner.demo;

import android.app.Application;

import com.wyjson.debug_banner.Banner;
import com.wyjson.debug_banner.BannerGravity;
import com.wyjson.debug_banner.DebugBanner;

/**
 * 调试旗帜
 * debug show checked mode banner
 *
 * @author Wyjson
 * @version 1
 * @date 2019-09-21 23:19
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            // 默认,全部页面显示
            DebugBanner.Companion.init(this);
        }

        // 过滤不显示的页面
//        DebugBanner.Companion.init(
//                this,
//                new Banner(),
//                false,
//                "MainActivity",
//                "ThreeActivity"
//        );

        // 过滤显示的页面
//        DebugBanner.Companion.init(
//                this,
//                new Banner(),
//                true,
//                "MainActivity",
//                "TwoActivity"
//        );

        // 自定义样式
//        DebugBanner.Companion.init(
//                this,
//                new Banner(BannerGravity.START, android.R.color.holo_blue_bright, android.R.color.holo_red_light, "BETA")
//        );
    }

}
