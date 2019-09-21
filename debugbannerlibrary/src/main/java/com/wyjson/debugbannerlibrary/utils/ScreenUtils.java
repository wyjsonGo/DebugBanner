package com.wyjson.debugbannerlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 *
 * @author wy
 */
public class ScreenUtils {
    private ScreenUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getRealMetrics(outMetrics);
//        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得状态栏的高度
     * wyjson:我就不信三种方法都获取不到
     *
     * @param context 上下文
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
        }
        if (statusBarHeight == 0) {
            try {
                Rect frame = new Rect();
                ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                statusBarHeight = frame.top;
            } catch (Exception e) {
            }
        }
        if (statusBarHeight == 0) {
            try {
                int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
                statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            } catch (Exception e) {
            }
        }

        return statusBarHeight;
    }

}
