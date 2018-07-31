package com.lopez.test.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lopez.test.utils.utils.ThreadPoolManager;
import com.lopez.test.utils.utils.ThreadUtils;
import com.lopez.test.utils.utils.ToastUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastUtils toast = ToastUtils.initToast(this);
        toast.setLayout(R.layout.toast_layout_white);
    }

    public void hello(View view) {
        ToastUtils.showToastShort("你好啊！");
    }

}
