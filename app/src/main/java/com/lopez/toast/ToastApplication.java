package com.lopez.toast;

import android.app.Application;
import android.util.Log;

import com.lopez.toast.utils.ToastUtils;


/**
 * Created by Lopez on 2018/7/31.
 */
public class ToastApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Log.d("==w", "准备初始化");
        ToastUtils.initToast(this);
    }

}
