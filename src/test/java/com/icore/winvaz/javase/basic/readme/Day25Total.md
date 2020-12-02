day25
1.	线程通信中的方法wait,notify为什么写在Object类中
  和锁有关系，同步中的锁是一个对象，而是什么对象，不确定的，因此方法写在了顶层的父类中，无论哪一个对象作为锁，都可以使用线程的方法
2.	wait方法和sleep方法有什么区别
  Thread类的静态方法sleep 无论线程休眠多长时间，线程不丢失对象监视器，不放锁
  wait方法，线程将发布对象监视器(的所有权并等待)，释放锁，被唤醒后，重新获取锁后，才能运行。

3.	多线程的通信，多生产者和多消费者案例
  有一个产品，生产一个，消费一个，生产者4线程，消费者4个线程
  notify唤醒线程的时候，往往是最先等待的线程
  线程从哪里等待，从哪里唤醒，也不能立即执行，进行标记的判断，如果标记允许生产，才能生产，允许消费才能消费
	保证能够唤醒对方的线程   全部唤醒notifyAll
	唤醒后，继续进行标记的判断 循环结构，线程继续判断标记
  导致资源的严重浪费
  唤醒了全部线程，浪费资源的
  唤醒了本方线程，没有任何意义
  做到唤醒对方中的一个呢？在JDK1.4版本之前，做不到
  1.5版本之后，可以实现唤醒对方的一个线程

4.	在1.5版本的时候，出现了一个新的锁
   新的锁，替换现在的同步机制 java.util.concurrent.locks包中，Lock接口
   Lock接口，替换的同步，更加广泛和灵活的锁定操作
   接口中的方法，lock()获取锁  unlock() 释放锁
   synchronize(this){ == 获取锁

   } == 释放锁
  找子类 ReentrantLock 接口指向实现类的方式

  既然同步没有了，this锁也就不存在了，替代品 java.util.concurrent.locks.Condition接口
  Condition接口中的方法
   await() == wait()
   signal() == nofify()
   signalAll() == notifyAll()
实现唤醒对方的一个线程，实现步骤：
	导包
	获取锁对象，Lock接口的实现类对象ReentrantLock
	Lock接口中的方法lock()  unlock()替换同步机制
	获取Condition对象，对线程进行分组管理，使用Lock接口中的方法new Condition获取接口的实现类对象
	利用Condition对象中的方法await signal实现等待与唤醒

5.	线程的停止方式
  Thread类方法stop过时，不用了
  结束线程，终止run方法的执行
	第一种，是改变循环变量
	第二种，利用异常  interrupt
  第二种方式，比喻，线程处于无限的等待，停不下来。
  线程看成是我们一个朋友，失眠，很严重的失眠，我找一个催眠大师，结果朋友和我区了，催眠大师很厉害，进行催眠，朋友进入了深度睡眠。
  催眠师说，除了我以外，任何催眠师都不能叫醒。催眠师死了。准备板砖，一板砖打下去，醒了，受伤了，流血了--异常了
  Thread类中方法，中断线程 void interrupt 处在等待状态的线程，打你一下子，打出异常来

6.	守护线程
 Thread类的方法 void setDaemon(boolean)传递的是true，将该线程标记为守护线程
 动画片圣斗士星矢，智慧女神雅典娜，88个圣斗士保护着，圣斗士保护雅典娜存在的。
 Thread 线程相当于是圣斗士, main线程相当于是雅典娜Athena，main结束了，雅典娜死了，圣斗士也就没有存在的意义了

7.	定时任务
  没到一个指定的时候，程序自动的去完成一个功能
  定时器. java.util.Timer实现定时运行程序
  Timer类的够造方法，设置成不是守护线程，构造方法传递false
  schedule()方法，定时运行的方法，三个参数 ，执行的代码，开始时间，间隔，毫秒

8.	Thread类的toString()方法，优先级
  toString(Thread-0, 5, main)方法，名字，优先级，线程组
  优先级，设置的优先级三个级别 最低1，默认5，最高10
  Thread方法 void setPriority(int )设置优先级

9.	join方法，yield方法
  join方法，等待该线程终止
  t0线程，t1线程，main线程，t0调用join方法
  t0先执行完毕，t1 main进行CPU的资源争夺

  static yield方法，线程的让步，线程把CPU的执行权礼让出去
  写在执行的线程中就可以了，不需要对象调用

10.	java.awt ， javax.swing 包
  Java将图形界面，包中的类，完成图形化界面的开发
  awt包中，类有什么特点：必须依赖操作系统，重量级组件
  swing包中，类，基于awt的功能，有Java代码完全实现的图形界面，不依赖操作系统，轻量级组件
 如果我们用awt做图形界面，Windows下的效果，就和Windows窗体效果是一致的，放到Linux中，界面的效果，就和Linux界面效果一致
 如果我们用swing做图形界面，Windows下的效果，和Linux下的效果是一致的

11.	Frame类，制作窗体
	空参数的
	带有一个String标题的
	让窗体显示的方法 setVisible(boolean )
	改变窗体大小的方法，设置尺寸 setSize(int width,int height)
	对窗体进行定位 setLocation(int x,int y)
	设置尺寸和位置 setBounds(int x ,int y, int width,int height)可以去掉setSize,setLocation
	设置窗体的背景色 setBackground(Color c)
万物皆对象，颜色也是对象，封装到了Color类
  获取屏幕分辨率的方式 ToolKit实现，静态方法 getDeafultToolKit,返回子类的对象
  调用Toolkit类的方法getScreenSize获取屏幕分辨率，返回Dimension对象，这个对象的成员变量 width height获取最终的分辨率

12.	布局管理器
  在窗体上，放置很多组件，文本框，按钮，排列，布局
13.	将按钮放在窗体上 
 描述按钮对象的类 java.awt.Button
 构造方法：带有一个String的标签
 将按钮放在窗体上，使用的是窗体的方法 Frame类的add(按钮)
 能不能让按钮，随意放置，大小自定义调节
 设置窗体的布局管理器，取消布局管理器
 调用窗体的方法 setLayout(null)
 如果取消了布局管理器，组件，必须定义位置和尺寸，否则不显示
14.	事件监听机制
	事件源：就是用户可以操作的组件，按钮，窗体，文本框
	事件：鼠标事件，键盘事件，窗体事件
	监听器：内置与事件源上的一种监听机制(窃听器)，监听鼠标事件，只要有鼠标的动作，就会被监听器捕获
	事件处理，一旦监听器监听到了动作，调用响应的函数，进行事件的处理
  有一个人，事件源，挨打事件，人的事件源中内部有一个监听机制，监听挨打，监听器捕获到了挨打的事件，打的部位和打的程度，调用不同的处理方法
15.	适配器的设计模式
  类适配器类，作用实现接口，重写接口中的全部抽象方法，但是重写后方法的主体为空。我们开发者不需要直接实现接口，继承适配器了，重写你想要的方法就可以了
  WindowListener接口 WindowAdapter类
	写事件监听器，标准格式：事件源.addXXXListener(new XXXAdapter(){
   public void 方法名(XXXEvent e){
}
});
16.	鼠标的事件
 点击鼠标，事件按钮的单击效果，双击，鼠标的移入和移出
 鼠标事件，事件接口，适配器，需要的方法