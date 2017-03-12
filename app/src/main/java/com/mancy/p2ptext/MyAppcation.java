package com.mancy.p2ptext;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;

/**
 * Created by Mancy on 2017/3/12.
 */

public class MyAppcation extends Application {

    private static Context context;
    private static Thread mainThread;
    private static int threadid;
    private static Handler handler;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;

    }

    public static Thread getMainThread() {
        return mainThread;

    }

    public static int getThreadid() {
        return threadid;

    }

    public static Handler getHandler() {
        return handler;

    }


    /**
     * 四种线程池
     *
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
