service 学习

学习生命周期，与service调用的关系和service的初步使用

1.创建一个继承Service的子类，例如：MyService这个类就是Service的子类

2.到清单文件中注册这个Service

<service android:name=".com.lifuz.service.MyService"/>

3.运行Service的两种方法

1). 使用startService的方法，代码如下：

startService(new Intent(this, MyService.class));

杀死Service的方法如下：

stopService(new Intent(this,MyService.class));

当我们第一次执行 startService方法，则生命周期运行顺序如下：
onCreate()
onStartCommand()
当这个Service已经被开启，再次执行startSer方法，Service的生命周期如下：
onStartCommand()

当我们执行 stopService方法，Service的生命周期如下：

onDestroy()

2). 通过bindService方法来启动Service。

首先在Service的子类里要做两件事：
①定义一个绑定的类并继承Binder类，这个类的作用是，与Activity进行交互
②重写onBind方法，使其返回Binder的子类对象，代码如下：

    /**
     * 当使用绑定启动Service的时候需绑定一个类
     * 下面的这个类就是我们将要绑定的类
     */
    public class MyBinder extends Binder{

        /**
         * 这个方法是模拟一个下载方法
         * 下载的连接有Activity赋值
         * @param url
         */
        public void startDownload(String url){
            Log.i("tag","开始下载URL为：" + url + "  的数据");
        }

    }

    private MyBinder mbind = new MyBinder();

    public IBinder onBind(Intent intent) {
          return mbind;
    }

在Activity里进行如下操作对Service进行绑定：

    //定义一个Service绑定类
    private MyService.MyBinder mbind;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //初始化Service绑定类
            mbind = (MyService.MyBinder) service;
            //调用绑定类中的方法并传值
            mbind.startDownload("www.prd.com");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    bindService(new Intent(this, MyService.class), conn, BIND_AUTO_CREATE);

解除Activity与Service的绑定：

    unbindService(conn);

ps：

这两种启动Service方法的对比：

①通过startService启动：Service与Activity无关联，只是单纯的启动Service，即使这个Activity被销毁但是Service
还是继续运行

②通过bindService方法绑定Service，则二者是同生共死的关系，即Activity被销毁，则Service也被销毁
而且通过这个方法绑定Service，可以使二者的关联更加紧密，上面的代码可以看出这些


4.创建前台Service



