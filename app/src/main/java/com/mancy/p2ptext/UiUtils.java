package com.mancy.p2ptext;

import android.content.Context;
import android.view.View;

/**
 * Created by Mancy on 2017/3/12.
 * <p>
 * 和Ui 相关的一些操作
 */


public class UiUtils {

    public static Context getContext() {
        return MyAppcation.getContext();

    }

    public static View getView(int layoutid) {
        return View.inflate(getContext(), layoutid, null);

    }

    public static int getColor(int color) {
        return getContext().getResources().getColor(color);
    }

    public static String[] getStringArrary(int stringid) {
        return getContext().getResources().getStringArray(stringid);

    }

    // dp 和 px 的转换  工具


    //与屏幕分辨率相关的
    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);

    }

    public static int px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }


}
