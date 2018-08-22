package com.lopez.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lopez.toast.utils.R;
import com.lopez.toast.utils.ThreadPoolManager;
import com.lopez.toast.utils.ThreadUtils;

import java.lang.ref.WeakReference;


/**
 * 一系列关于Toast的封装
 * Created by Lopez on 2017/8/10.
 */

public class ToastUtils {

    private static Toast mToast;
    private static ToastUtils instance;
    private MyHandler mHandler;
    private static WeakReference<Context> weakContext;

    public static ToastUtils initToast(Context context) {
        weakContext = new WeakReference<>(context);
        return getInstance(weakContext.get());
    }

    private static ToastUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (ToastUtils.class) {
                Log.d("==w", "instance为空 > 1 again");
                if (instance == null) {
                    Log.d("==w", "instance为空 > 2 实例化");
                    instance = new ToastUtils(context);
                }
            }
        }
        return instance;
    }

    private ToastUtils(Context context) {
        setHandler(new MyHandler(context));
    }

    private void setHandler(MyHandler mHandler) {
        this.mHandler = mHandler;
    }

    public MyHandler getHandler() {
        return mHandler;
    }

    public void setLayout(int layoutId) {
        if (mHandler == null) return;
        mHandler.setLayout(layoutId);
    }

    /**
     * 核心的Handler类，采用弱引用以避免内存泄露
     */
    private static class MyHandler extends Handler {

        private WeakReference<Context> weakReference;
        private View view;
        LayoutInflater inflater;

        public void setLayout(int resourceId) {
            this.view = inflater.inflate(resourceId, null);
        }

        MyHandler(Context context) {
            weakReference = new WeakReference<>(context);
            inflater = (LayoutInflater) weakReference.get().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (mToast != null) {
                        Log.d("==w", "handleMessage: mToast不为空");
                        toastShow();

                        ThreadPoolManager.execute(new Runnable() {
                            @Override
                            public void run() {
                                count += 0.1;

                                if (count <= time) {
                                    SystemClock.sleep(100);
                                    Log.d("==w", ">> handleMessage: count: " + count + "\t\tVS\t\ttime: " + time);
                                    getInstance(weakContext.get()).getHandler().sendEmptyMessage(0);
                                } else {
                                    Log.d("==w", "handleMessage: 结束了。。。");
                                    //重置
                                    count = 0;
                                    if (mToast != null) {
                                        mToast.cancel();
                                        mToast = null;
                                    }
                                }

                                String threadName = Thread.currentThread().getName();
                                Log.d("==w", "run: 当前线程名: " + threadName + "\n");
                            }
                        });
                    }
                    break;
            }
        }

        /**
         * 随意设置吐司的时间（多少秒都可以，如果你不是蛋找疼）
         *
         * @param msg         要显示的文字
         * @param millisecond 吐司持续时长(毫秒)
         */
        private void showMyToast(String msg, final long millisecond) {
            if (inflater == null) return;
            //自定义吐司布局
            if (view == null) {
                view = inflater.inflate(R.layout.toast_layout, null);
            }

            TextView text = (TextView) view.findViewById(R.id.toast_message);//显示的提示文字
            text.setText(msg);

            //每次吐司都先去掉Handler的回调,防止频繁的操作导致空指针
            getInstance(weakContext.get()).getHandler().removeCallbacks(r);

            //只有 mToast==null 时才重新创建，否则只需更改提示文字
            if (mToast == null) {
                mToast = new Toast(weakReference.get());
                mToast.setDuration(Toast.LENGTH_LONG);
                //mToast.setGravity(Gravity.CENTER, 0, 555); //不设置就会自己去适配显示的高度
                mToast.setView(view);

            } else {
//            !msg.equals(text.getText())
                View mToastView = mToast.getView();
                TextView mtv = (TextView) mToastView.findViewById(R.id.toast_message);
                if (!mtv.getText().equals(msg)) {
                    Log.d("==w", "showMyToast: 与之前的值不相等: " + mtv.getText());
                    //不相等就表示可以更新Toast了
                    mtv.setText(msg);
                }
            }

            getInstance(weakContext.get()).getHandler().postDelayed(r, millisecond);//延迟ms秒隐藏toast

            isInMainThread = Thread.currentThread() == Looper.getMainLooper().getThread();

            if (millisecond <= 3500) {
                toastShow();

            } else {
                //大于3.5秒的才是要换算法的，为了避免某些世界长的后蓄力不足，特加此句
                getInstance(weakContext.get()).getHandler().removeMessages(0);
                //每次点击按钮都要重新清0
                count = 0;

                if (mToast != null) {
                    toastShow();

                    //这个是表示 传入的世界一共有多少秒
                    time = (float) ((millisecond) / 1000.0);

                    count += 0.1;

                    ThreadPoolManager.execute(new Runnable() {
                        @Override
                        public void run() {
                            //表示0.1秒跑一次
                            SystemClock.sleep(100);
                            Log.d("==w", "run: 发送 0 !!");
                            getInstance(weakContext.get()).getHandler().sendEmptyMessage(0);
                        }
                    });
                }
            }
        }

        /**
         * 从资源文件拿到文字
         */
        private String getResourceString(int strId) {
            String result = "";
            try {
                result = weakReference.get().getResources().getString(strId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

    }


    //主线、子线程中的封装Toast
    private static void toastShow() {
        if (isInMainThread) {
            Log.d("==w", "主线程 --- Toast");
            mToast.show();
        } else {
            try {
                Log.d("==w", "如果处在子线程，那么需要换到主线程中再Toast");
                ThreadUtils.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        mToast.show();
                    }
                });
            } catch (Exception e) {
                Log.d("==w", "Toast错误...");
                e.printStackTrace();
            }
        }
    }

    //使Toast到时间了就消失掉
    private static Runnable r = new Runnable() {
        public void run() {
            if (mToast != null) {
                mToast.cancel();
            }

            mToast = null;//toast隐藏后，将其置为null
        }
    };


    private static float count = 0;
    private static float time = 0;
    private static boolean isInMainThread;


    /**
     * ===============================    对Toast吐司的封装    ==================================
     */

    /**
     * 显示用户自定义秒数的吐司
     *
     * @param msg         要显示的内容
     * @param millisecond 毫秒数
     */
    public static void showToast(String msg, int millisecond) {
        getInstance(weakContext.get()).getHandler().showMyToast(msg, millisecond);
    }

    /**
     * 显示2S的吐司
     *
     * @param msg 要显示的内容
     */
    public static void showToast(String msg) {
        //不能直接填SHORT、LONG这样的标识符
        getInstance(weakContext.get()).getHandler().showMyToast(msg, 2000);
    }

    /**
     * 显示3.5S的吐司
     *
     * @param msg 要显示的内容
     */
    public static void showToastLong(String msg) {
        //不能直接填标识符，不然会失效(那些表示0、1)
        Log.d("==w", "toast: " + msg);
        getInstance(weakContext.get()).getHandler().showMyToast(msg, 3500);
    }


    /**
     * 显示用户自定义秒数的吐司
     *
     * @param resId       要显示的内容
     * @param millisecond 毫秒数
     */
    public static void showToast(int resId, int millisecond) {
        getInstance(weakContext.get()).getHandler().showMyToast(getInstance(weakContext.get()).getHandler().getResourceString(resId), millisecond);
    }

    /**
     * 显示2S的吐司
     *
     * @param resId 要显示的内容id
     */
    public static void showToast(int resId) {
        //不能直接填SHORT、LONG这样的标识符
        getInstance(weakContext.get()).getHandler().showMyToast(getInstance(weakContext.get()).getHandler().getResourceString(resId), 2000);
    }

    /**
     * 显示3.5S的吐司
     *
     * @param resId 要显示的内容id
     */
    public static void showToastLong(int resId) {
        //不能直接填标识符，不然会失效(那些表示0、1)
        getInstance(weakContext.get()).getHandler().showMyToast(getInstance(weakContext.get()).getHandler().getResourceString(resId), 3500);
    }


}
