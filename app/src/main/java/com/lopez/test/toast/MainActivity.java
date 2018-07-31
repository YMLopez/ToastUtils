package com.lopez.test.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.lopez.test.utils.utils.ToastUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastUtils toast = ToastUtils.initToast(this);
        toast.setLayout(R.layout.toast_layout_white);

        //ToastUtils.initToast(this).setLayout(R.layout.toast_layout_white);
    }

    public void hello(View view) {
        //ToastUtils.showToast("你好啊！");
        //ToastUtils.showToast(R.string.hi, 1000);
        ToastUtils.showToastLong("我很菜~");
    }

}
