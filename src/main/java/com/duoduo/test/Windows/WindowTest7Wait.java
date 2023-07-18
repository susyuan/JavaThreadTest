package com.duoduo.test.Windows;


/*
    实现不连续售票，先唤醒所有再阻塞自己
 */

class Window7 implements Runnable{
    private  int Ticket = 100;
    private Object obj = new Object();

    @Override
    public void run() {
        while(true) {
            synchronized (obj) {
                if(Ticket > 0){
                    obj.notifyAll();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() +
                            "：售票，票号为：" + Ticket);
                    Ticket--;
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else break;
            }
        }
    }
}
public class WindowTest7Wait {
    public static void main(String[] args) {
        Window7 w = new Window7();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
