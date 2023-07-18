package com.duoduo.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
    创建线程的方式三：实现Callable接口

    对比Runnable
    1. call()可以有返回值的。
    2. call()可以抛出异常，被外面的操作捕获，获取异常的信息
    3. Callable是支持泛型的
 */
//1.创建一个实现Callable的实现类
class MyThread3 implements Callable {
    @Override
    public Integer call() throws Exception {

        return 100;
    }
}
class MyThread4 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {//可以定义返回值类型
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() +":" + i);
                sum += i;
            }
        }
        return sum;
    }
}
public class Demo3Callable {
    public static void main(String[] args) {
        //3.创建Callable接口实现类的对象
        MyThread3 myThread3 = new MyThread3();
        MyThread4 myThread4 = new MyThread4();
        //4.将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask<Integer> futureTask = new FutureTask<>(myThread3);
        FutureTask<Integer> futureTask1 = new FutureTask<>(myThread4);
        //5.将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        Thread thread = new Thread(futureTask);
        thread.setName("线程1");
        thread.start();

        Thread thread1 = new Thread(futureTask1);
        thread1.setName("线程2");
        thread1.start();

        Integer integer = null;
        try {
            //6.获取Callable中call方法的返回值
            //get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。
            //get 要处理异常
            integer = futureTask.get();
            Integer sum = futureTask1.get();
            System.out.println(sum);
            System.out.println(integer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}

