package com.duoduo.test;
/*
创建两个分线程 ，让其中 一 个线程输出 1~100之间的偶数，另 一
个线程输出1~100 之间的奇数。
没有线程同步问题 直接start
 */
public class Test3 {
    public static void main(String[] args) {
        new Thread1("线程1").start();
        new Thread2("线程2").start();

        //创建Thread类的匿名子类的方式
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i % 2 == 0){
                        System.out.println(Thread.currentThread().getName() + ":" + i);

                    }
                }
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i % 2 != 0){
                        System.out.println(Thread.currentThread().getName() + ":" + i);

                    }
                }
            }
        }.start();
    }
}
class Thread1 extends  Thread{
    Thread1 (String name){
        super(name);
    }
    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            if(i % 2 == 1)
                System.out.println(Thread.currentThread().getName() +" " + i );
        }
    }
}
class Thread2 extends  Thread{
    Thread2 (String name){
        super(name);
    }
    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            if(i % 2 == 0)
                System.out.println(Thread.currentThread().getName() +" " + i );
        }
    }
}