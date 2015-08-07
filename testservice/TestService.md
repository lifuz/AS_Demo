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





