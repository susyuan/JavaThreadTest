package com.duoduo.test.Windows;
/**
 * 使用同步代码块解决继承Thread类的方式的线程安全问题
 *
 * 例子：创建三个窗口卖票，总票数为100张.使用继承Thread类的方式
 *
 * 说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器。
 *
 *
 */
class Window2 extends Thread {
    private static  int Ticket = 100;
    private static Object obj = new Object();
    @Override
    public void run() {
        while(true) {
            synchronized (obj) {
                //不能用this 此时t1 t2 t3三个对象
                // Window2.class 效果一样
                if(Ticket > 0){
                    try {
                        Thread.sleep(100);//让窗口跳动慢一点
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"票号:" + Ticket);
                    Ticket--;
                }
                else break;//票卖完跳出循环
            }
            // synchronized (Window2.class) {
            //     if(Ticket > 0){
            //         try {
            //             Thread.sleep(100);//让窗口跳动慢一点
            //         } catch (InterruptedException e) {
            //             e.printStackTrace();
            //         }
            //         System.out.println(Thread.currentThread().getName()+"票号:" + Ticket);
            //         Ticket--;
            //     }
            //     else break;//票卖完跳出循环
            // }
        }
    }
}
public class WindowTest2 {
    public static void main(String[] args) {
        Window2 t1 = new Window2();
        Window2 t2 = new Window2();
        Window2 t3 = new Window2();


        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
