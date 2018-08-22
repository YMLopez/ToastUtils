package com.lopez.toast;

import android.app.Application;


/**
 * Created by Lopez on 2018/7/31.
 */
public class ToastApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Log.d("==w", "准备初始化");
        ToastUtils toast = ToastUtils.initToast(this);
        toast.setLayout(R.layout.toast_layout_white);
    }

}
