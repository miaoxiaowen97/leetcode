package com.mxw.leetcode.practice;

public class Singleton {

    private static volatile Singleton uniSingleton;

    private  Singleton(){}

    public static Singleton getUniSingleton() {
        if (uniSingleton==null){
            synchronized (Singleton.class){
                if (uniSingleton==null){
                    // 分配内存空间
                    // 初始化
                    // 指向内存地址
                    // 指令重排顺序： 防止上述顺序被打乱，导致多线程拿到没有没有初始化的对象
                    uniSingleton=new Singleton();
                }
            }
        }
        return uniSingleton;
    }
}
