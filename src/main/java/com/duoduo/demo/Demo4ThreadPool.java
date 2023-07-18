package com.duoduo.demo;

import java.util.concurrent.*;
/*
    创建线程的方式四：使用线程池
    好处：
    1.提高响应速度（减少了创建新线程的时间）
    2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
    3.便于线程管理
       corePoolSize：核心池的大小
       maximumPoolSize：最大线程数
       keepAliveTime：线程没有任务时最多保持多长时间后会终止
 */
public class Demo4ThreadPool {
    public static void main(String[] args) {
        //1. 提供指定线程数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println(executorService.getClass());
        // 设置线程池的属性 需要用 ThreadPoolExecutor接口
        ThreadPoolExecutor service = (ThreadPoolExecutor) executorService;
        service.setCorePoolSize(15);
        service.setKeepAliveTime(100, TimeUnit.MINUTES);
        //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        executorService.execute(new MyThread5());//适合runnable接口
        executorService.execute(new MyThread5());
        executorService.execute(new MyThread5());
        executorService.submit(new MyThread6());
        //3.关闭线程池
        service.shutdown();
    }
}
class MyThread5 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
class MyThread6 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return 200;
    }
}
