package com.mxw.leetcode.前缀差数组;

public class 拼车 {
    /**
     你是一个开公交车的司机，公交车的最大载客量为 capacity，沿途要经过若干车站，给你一份乘客行程表 int[][] trips，
     其中 trips[i] = [num, start, end] 代表着有 num 个旅客要从站点 start 上车，到站点 end 下车，
     请你计算是否能够一次把所有旅客运送完毕（不能超过最大载客量 capacity）。

     输入：trips = [[2,1,5],[3,3,7]], capacity = 4
     输出：false
     第一站上了2个，还是2个，但是第二站要上三个就不行
     这就不能一次运完，因为 trips[1] 最多只能上 2 人，否则车就会超载

     输入：trips = [[2,1,5],[3,3,7]], capacity = 5
     输出：true
     */

    boolean carPooling(int[][] trips, int capacity) {

        // 最多有 1001 个车站
        int[] nums = new int[1001];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] trip : trips) {
            // 第 trip[1] 站乘客上车
            int i = trip[1];
            // 第 trip[2] 站乘客已经下车，
            // 即乘客在车上的区间是 [trip[1], trip[2] - 1]
            int j = trip[2] - 1;
            // 乘客数量
            int val = trip[0];
            // 进行区间操作
            df.increment(i, j, val);
        }
        int[] res = df.result();

        // 客车自始至终都不应该超载
        for (int i = 0; i < res.length; i++) {
            if (capacity < res[i]) {
                return false;
            }
        }
        return true;
    }

    // 差分数组工具类
    class Difference {
        // 差分数组
        private int[] diff;

        /* 输入一个初始数组，区间操作将在这个数组上进行 */
        public Difference(int[] nums) {
            assert nums.length > 0;
            diff = new int[nums.length];
            // 根据初始数组构造差分数组
            diff[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /* 给闭区间 [i, j] 增加 val（可以是负数）*/
        public void increment(int i, int j, int val) {
            diff[i] += val;
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        /* 返回结果数组 */
        public int[] result() {
            int[] res = new int[diff.length];
            // 根据差分数组构造结果数组
            res[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }
            return res;
        }
    }
}
