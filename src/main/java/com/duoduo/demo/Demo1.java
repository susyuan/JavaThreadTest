package com.duoduo.demo;
/*
    创建线程方式1 继承Thread类
    线程1 2 出现的顺序不一定
 */
public class Demo1 {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("线程1");
        MyThread thread2 = new MyThread();
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

class MyThread extends  Thread {
    MyThread (String name){
        super(name);
    }
    MyThread ( ){

    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
