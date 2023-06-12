package com.mxw.leetcode.practice;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatoleAtomicityDemo {

    public static  volatile int inc=0;

    public void increase(){
        inc++;
    }

    public static void main(String[] args) throws Exception{
        // 线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        VolatoleAtomicityDemo demo = new VolatoleAtomicityDemo();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(()->{
                for (int i1 = 0; i1 < 500; i1++) {
                    // 读取 inc 的值
                    // 对 inc 加 1
                    // 将 inc 的值写回内存

                    /**
                     * 线程 1 对 inc 进行读取操作之后，还未对其进行修改。
                     * 线程 2 又读取了 inc的值并对其进行修改（+1），再将inc 的值写回内存。
                     * 线程 2 操作完毕后，线程 1 对 inc的值进行修改（+1），再将inc 的值写回内存。
                     * 这也就导致两个线程分别对 inc 进行了一次自增操作后，inc 实际上只增加了 1
                     *
                     * 升级：
                     * public synchronized void increase() {
                     *     inc++;
                     * }
                     * 或者：
                     * Lock lock = new ReentrantLock();
                     * public void increase() {
                     *     lock.lock();
                     *     try {
                     *         inc++;
                     *     } finally {
                     *         lock.unlock();
                     *     }
                     * }
                     *
                     * 或者：
                     * public AtomicInteger inc = new AtomicInteger();
                     *
                     * public void increase() {
                     *     inc.getAndIncrement();
                     * }
                     */
                    demo.increase();
                }
            });
        }
        // 等待1.5秒，保证上面程序执行完成
        Thread.sleep(1500);
        System.out.println(inc);
        threadPool.shutdown();
    }
}
