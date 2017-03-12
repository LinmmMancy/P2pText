package com.mancy.p2ptext;

import android.util.Log;

/**
 * Created by Mancy on 2017/3/12.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {


    private CrashHandler() {
    }

    ;


    private static CrashHandler crashHandler = new CrashHandler();

    public static CrashHandler getInstance() {
        return crashHandler;
    }

    public void init() {

        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("TAG", "uncaughtException: ");
    }
}
