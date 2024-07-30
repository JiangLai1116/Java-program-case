package com.jactil.javaprogramcase.se;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolDemo {
    //使用阻塞队列充当线程池
    private static BlockingQueue<Thread> blockingQueue = new LinkedBlockingQueue(1000000);
    private static ExecutorService executorService = Executors.newCachedThreadPool();//定义缓存线程池
    static {
        try {
            Thread t = blockingQueue.take();//阻塞队列中取线程
            while (t != null) {
                System.out.println("do something!");
                executorService.submit(t);
                //控制取数速率
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTask() {
        blockingQueue.add(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("add a task!");
            }
        }));
    }

}
