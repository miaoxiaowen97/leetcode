package com.mxw.leetcode.practice;

public class ReentrantLock {
    /*
    ReentrantLock 里面有一个内部类 Sync，Sync 继承 AQS（AbstractQueuedSynchronizer），
    添加锁和释放锁的大部分操作实际上都是在 Sync 中实现的。Sync 有公平锁 FairSync 和非公平锁 NonfairSync 两个子类

    可重入锁：连续拿两次相同的锁
    public class ReentrantLockDemo {
            public synchronized void method1() {
                System.out.println("方法1");
                method2();
            }

            public synchronized void method2() {
                System.out.println("方法2");
            }
     }
     由于 synchronized锁是可重入的，同一个线程在调用method1() 时可以直接获得当前对象的锁，
     执行 method2() 的时候可以再次获取这个对象的锁，不会产生死锁问题。
     假如synchronized是不可重入锁的话，由于该对象的锁已被当前线程所持有且无法释放，这就导致线程在执行 method2()时获取锁失败，会出现死锁问题

     增加：
     等待可中断：lock.lockInterruptibly()  正在等待的线程可以选择放弃等待
     可实现公平锁 ：

     */
}
