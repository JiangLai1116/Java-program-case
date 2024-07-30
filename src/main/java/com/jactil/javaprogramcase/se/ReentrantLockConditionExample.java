package com.jactil.javaprogramcase.se;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2024-7-30 14:13:40
 * ReentrantLock和Condition的使用
 */
public class ReentrantLockConditionExample {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            lock.lock();// 获取锁
            try {
                System.out.println("Thread 1 is waiting on condition...");
                condition.await(); // 等待条件
                System.out.println("Thread 1 has been notified!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 2 is about to signal condition...");
                condition.signal(); // 通知条件
                System.out.println("Thread 2 has signaled condition!");
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}
