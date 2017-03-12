package com.mancy.p2ptext;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

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


        new Thread() {
            @Override
            public void run() {
                super.run();

                Looper.prepare();

                Toast.makeText(UiUtils.getContext(), "aaa", Toast.LENGTH_SHORT).show();

                Looper.loop();
            }
        }.start();

        collection(e);

        //摧毁所有的activity
        AppManager.getInstance().removeAll();

        // 结束当前的  进程

        android.os.Process.killProcess(android.os.Process.myPid());

        //关闭虚拟机  .0 是正常退出

        System.exit(0);


    }

    private void collection(Throwable e) {

    }
}
