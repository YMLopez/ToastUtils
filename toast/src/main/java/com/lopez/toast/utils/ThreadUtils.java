package com.lopez.toast.utils;

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

    public static void runInUIThread(Runnable runnable) {
        runInUIThread(runnable, true);
    }

    public static void runInUIThread(Runnable runnable, boolean forcepost) {
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

    public static void runInUIThreadDelay(Runnable runnable, long delay) {
        if (runnable == null) {
            return;
        }
        initMainHandler();
        sMainHandler.postDelayed(runnable, delay);
    }

    public static void removeFromUIThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        initMainHandler();
        sMainHandler.removeCallbacks(runnable);
    }

    public synchronized static void initMainHandler() {
        if (sMainHandler == null) {
            sMainHandler = new Handler(Looper.getMainLooper());
        }
    }

    public static void runInGlobalWorkThread(Runnable runnable) {
        runInGlobalWorkThread(runnable, true);
    }

    public static void runInGlobalWorkThread(Runnable runnable, boolean forcepost) {
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

    public static void runInGlobalWorkThreadDelay(Runnable runnable, long delay) {
        if (runnable == null) {
            return;
        }
        initWorkHandler();
        sWorkHandler.postDelayed(runnable, delay);
    }

    public synchronized static void removeFromGlobalWorkThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        initWorkHandler();
        sWorkHandler.removeCallbacks(runnable);
    }

    public synchronized static void initWorkHandler() {
        if (sWorkHandler == null) {
            sWorkThread = new HandlerThread("com.rekall.extramessage.util.ThreadUtils");
            sWorkThread.start();
            sWorkHandler = new Handler(sWorkThread.getLooper());
        }
    }
}
