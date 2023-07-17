package com.duoduo.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
Callable
 */
public class Demo3 {
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread3());
        Thread thread = new Thread(futureTask);
        thread.setName("线程1");
        thread.start();
        Integer integer = null;
        try {
            //get 要处理异常
            integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
class MyThread3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return 200;
    }
}
