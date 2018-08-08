package com.lopez.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lopez.toast.utils.ToastUtils;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ToastUtils toast = ToastUtils.initToast(this);
        toast.setLayout(R.layout.toast_layout_white);*/
    }

    public void hello(View view) {
        //ToastUtils.showToast("你好啊！");
        //ToastUtils.showToast(R.string.hi, 1000);
        ToastUtils.showToastLong("我很菜呀~");
    }

}
