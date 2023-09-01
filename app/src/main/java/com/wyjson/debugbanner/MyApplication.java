package com.wyjson.debugbanner;

import android.app.Application;
import android.os.Process;

import com.wyjson.debug_banner.Banner;
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
        runOnWorkThread(initThirdServiceRunnable);
    }

    private void runOnWorkThread(Runnable action) {
        new Thread(action).start();
    }

    /**
     * 子线程初始化,优化启动
     */
    private final Runnable initThirdServiceRunnable = new Runnable() {
        @Override
        public void run() {
            //设置线程的优先级，不与主线程抢资源
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

            /**
             * 默认,全部页面显示
             */
//            DebugBanner.Companion.init(MyApplication.this, new Banner());

            /**
             * 过滤不显示的页面
             */
//            DebugBanner.Companion.init(MyApplication.this,
//                    new Banner(),
//                    false,
//                    "MainActivity",
//                    "ThreeActivity"
//            );

            /**
             * 过滤显示的页面
             */
            DebugBanner.Companion.init(
                    MyApplication.this,
                    new Banner(),
                    true,
                    "MainActivity",
                    "TwoActivity"
            );

            /**
             * 自定义样式
             */
//            DebugBanner.Companion.init(
//                    MyApplication.this,
//                    new Banner(BannerGravity.START, android.R.color.holo_blue_bright, android.R.color.holo_red_light, "BETA")
//            );

            /**
             * release打包不显示
             */
//            if (BuildConfig.DEBUG) {
//                DebugBanner.Companion.init(MyApplication.this, new Banner());
//            }

        }
    };
}
