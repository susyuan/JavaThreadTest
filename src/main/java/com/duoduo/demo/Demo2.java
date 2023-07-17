package com.duoduo.demo;
/*
 创建线程方式2：实现Runnable接口
 */
public class Demo2 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyThread1());
        thread1.setName("线程1");
        thread1.start();
    }
}
class MyThread1 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
