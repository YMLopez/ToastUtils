package com.lopez.test.utils.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * 线程工具类
 */

public class ThreadUtils {

    private static HandlerThread sWorkThread;
    private static Handler sWorkHandler;
    private static Handler sMainHandler;

    protected static void runInUIThread(Runnable runnable) {
        runInUIThread(runnable, true);
    }

    protected static void runInUIThread(Runnable runnable, boolean forcepost) {
        if (runnable == null) {
            return;
        }
        initMainHandler();
        if (Looper.myLooper() == Looper.getMainLooper() && !forcepost) {
            runnable.run();
        } else {
            sMainHandler.post(runnable);
        }
    }

    protected static void runInUIThreadDelay(Runnable runnable, long delay) {
        if (runnable == null) {
            return;
        }
        initMainHandler();
        sMainHandler.postDelayed(runnable, delay);
    }

    protected static void removeFromUIThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        initMainHandler();
        sMainHandler.removeCallbacks(runnable);
    }

    protected synchronized static void initMainHandler() {
        if (sMainHandler == null) {
            sMainHandler = new Handler(Looper.getMainLooper());
        }
    }

    protected static void runInGlobalWorkThread(Runnable runnable) {
        runInGlobalWorkThread(runnable, true);
    }

    protected static void runInGlobalWorkThread(Runnable runnable, boolean forcepost) {
        if (runnable == null) {
            return;
        }
        initWorkHandler();
        if (Looper.myLooper() == sWorkThread.getLooper() && !forcepost) {
            runnable.run();
        } else {
            sWorkHandler.post(runnable);
        }
    }

    protected static void runInGlobalWorkThreadDelay(Runnable runnable, long delay) {
        if (runnable == null) {
            return;
        }
        initWorkHandler();
        sWorkHandler.postDelayed(runnable, delay);
    }

    protected synchronized static void removeFromGlobalWorkThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        initWorkHandler();
        sWorkHandler.removeCallbacks(runnable);
    }

    private synchronized static void initWorkHandler() {
        if (sWorkHandler == null) {
            sWorkThread = new HandlerThread("com.rekall.extramessage.util.ThreadUtils");
            sWorkThread.start();
            sWorkHandler = new Handler(sWorkThread.getLooper());
        }
    }
}
