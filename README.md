# Toast
简单方便的Toast工具类
----------

### **1.效果图预览**
</br>
![效果1](<img src="https://github.com/YMLopez/TheTest/blob/master/0.jpg" width="20%" height="40%" />)

![效果2](<img src="https://github.com/YMLopez/TheTest/blob/master/1.jpg" width="20%" height="40%" />)

![效果3](<img src="https://github.com/YMLopez/TheTest/blob/master/2.jpg" width="20%" height="40%" />)
</br>
----------


### **2.初始化**
先在你的Application初始化，当然也可以在BaseActivity，但是这样就不适用于Fragment了
</br>
</br>
> //初始化，建议在Application初始化
> ToastUtils toast = ToastUtils.initToast(this);

----------

### **3.设置自定义布局**
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

### **4.进去调用吧**
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

### **5.如何使用**
在你Project的gradle里面添加：
</br></br>

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

</br>
</br>
在你app的gradle里面添加：
</br></br>

dependencies {
	        implementation 'com.github.YMLopez:ToastUtils:1.0.0'
	}

</br></br>

----------

**目前已知的Bug：**
1.虽然可以在子线程中Toast，但是不是一定能成功出来的，想要出来还得在它子线程前面的UI线程toast一下，不过不会在子线程中Toast直接崩溃就是了;
2.在子线程模式下疯狂连按，很快就会崩，不过很奇怪的是我项目原始用的不崩，通过gradle引用依赖调用的话就会崩...

----------


好了，我知道我里面的代码写得不是很好(压根就不好好吧,这么明显的bug都不修,你肯定只是够自己的需求就行了！)，然后相关的很多功能都没有加上去。不过现在项目忙就懒得改了，有什么不好的地方请各位大佬勿喷，请多指教，哈哈^_^。这个leanote的markdown真是...以后有空再改好看点吧...更多功能及使用请自己探索吧......





