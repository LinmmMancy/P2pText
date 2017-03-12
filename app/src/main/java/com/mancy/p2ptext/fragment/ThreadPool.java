package com.mancy.p2ptext.fragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Mancy on 2017/3/12.
 */

public class ThreadPool {

    private ThreadPool() {


    }

    private static ThreadPool threadPool = new ThreadPool();

    public static ThreadPool getThreadPool() {
        return threadPool;
    }

    private ExecutorService executorService
            = Executors.newCachedThreadPool();

    public ExecutorService getGlobalThread() {
        return executorService;
    }
}
