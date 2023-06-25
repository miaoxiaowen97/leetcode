package com.mxw.leetcode.图.entity;

import com.mxw.leetcode.图.T04课程表扁平化图;

public class testMain {

    public static void main(String[] args) {
        T04课程表扁平化图 demo=new T04课程表扁平化图();
        int[][] prerequisites=new int[][]{{1,0},{2,0},{3,1},{3,2}};
        demo.findOrder(4,prerequisites);
    }
}
