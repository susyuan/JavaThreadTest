package com.duoduo.demo;
/*
    多线程的创建，方式一：继承于Thread类
    1. 创建一个继承于Thread类的子类
    2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
    3. 创建Thread类的子类的对象
    4. 通过此对象调用start()
    线程1 2 出现的顺序不一定
 */
//1. 创建一个继承于Thread类的子类
class MyThread extends  Thread {
    MyThread (String name){
        super(name);
    }
    MyThread ( ){

    }
    //2. 重写Thread类的run()
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

public class Demo1Thread {
    public static void main(String[] args) {
        //3. 创建Thread类的子类的对象
        MyThread thread1 = new MyThread("线程1");
        MyThread thread2 = new MyThread();
        //4.通过此对象调用start():①启动当前线程 ② 调用当前线程的run()
        thread1.start();
        thread2.setName("线程2");
        thread2.start();
        //创建线程的匿名子类方式
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
    }
}

