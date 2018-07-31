# Toast
简单方便的Toast工具类

----------


### **1.初始化**
先在你的Application初始化，当然也可以在BaseActivity，但是这样就不适用于Fragment了
</br>
</br>
> //初始化，建议在Application初始化
> ToastUtils toast = ToastUtils.initToast(this);

----------

### **2.设置自定义布局**
当然也可以不写，这样就会使用默认的（ps:不是传统Android的that，是我感觉看着不错的this）
</br>
</br>
>//设置自定义的Toast布局，里面暂时只支持1个textview
> toast.setLayout(R.layout.toast_layout_white);

</br>
或者直接一行设置完毕
</br>
</br>
> ToastUtils.initToast(this).setLayout(R.layout.toast_layout_white);

----------

### **3.进去调用吧**
默认2秒钟的：
</br>
</br>
> ToastUtils.showToast("你好啊！");

</br>
</br>
默认3.5秒钟的：
</br>
</br>
> ToastUtils.showToastLong("我很菜~");

</br>
</br>*也可以自定义显示多少毫秒的：*
</br>
</br>
> //显示1秒(1000ms) 
> ToastUtils.showToast(R.string.hi, 1000);

</br>

----------

### **4.如何使用**
在你Project的gradle里面添加：
</br></br>

> allprojects {  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;repositories { 		 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;... 		 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;maven { url 'https://jitpack.io' } 		 &nbsp; &nbsp; &nbsp;
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}
}

</br>
</br>
在你app的gradle里面添加：
</br></br>

> dependencies {
> 	        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;implementation 'com.github.YMLopez:Toast:1.0.0' 
}

</br></br>

----------


好了，我知道我里面的代码写得不是很好，不过现在项目忙就懒得改了，哈哈，更多使用及功能请自己探索吧...





