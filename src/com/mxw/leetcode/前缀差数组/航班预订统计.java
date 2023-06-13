package com.mxw.leetcode.前缀差数组;

public class 航班预订统计 {
    /**
     这里有n个航班，它们分别从 1 到 n 进行编号。
     有一份航班预订表bookings ，表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]
     意味着在从 firsti到 lasti包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。
     请你返回一个长度为 n 的数组answer，里面的元素是每个航班预定的座位总数

     输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
     输出：[10,55,45,25,25]

     航班编号        1   2   3   4   5
     预订记录 1 ：   10  10
     预订记录 2 ：       20  20
     预订记录 3 ：       25  25  25  25
     总座位数：      10  55  45  25  25
     因此，answer = [10,55,45,25,25]

     */

    int[] corpFlightBookings(int[][] bookings, int n) {
        // nums 初始化为全 0
        int[] nums = new int[n];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] booking : bookings) {
            // 注意转成数组索引要减一哦
            int i = booking[0] - 1;
            int j = booking[1] - 1;
            int val = booking[2];
            // 对区间 nums[i..j] 增加 val
            df.increment(i, j, val);
        }
        // 返回最终的结果数组
        return df.result();
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
