package com.duoduo.test;
/*
N个线程顺序循环打印0~ 100
 */
class NumberThread implements Runnable {
    private static int number = 0 ;
    @Override
    public void run() {
        while(true) {
            synchronized (this) {

                this.notifyAll();
                if(number<=100) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                }
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
public class Test5PrintTest {
    public static void main(String[] args) {
        NumberThread t = new NumberThread();
        for(int i = 0; i < 20 ; i++) {
            new Thread(t,"线程" + i).start();
        }
    }
}
