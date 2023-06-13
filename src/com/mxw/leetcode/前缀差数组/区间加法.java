package com.mxw.leetcode.前缀差数组;

public class 区间加法 {
    /**
     * 假设你有一个长度为 n的数组，初始情况下所有的数字均为 0，你将会被给出k个更新的操作
     * 其中，每个操作会被表示为一个三元组: [startlndex,endindex,inc]，
     * 你需要将子数组A[startIndex ...endIndex] (包括startlndex 和 endlndex) 增加inc。
     * 请你返回k次操作后的数组
     *
     * 输入: length = 5，updates = [[1,3,2],[2,4,3],[0,2,-2]]
     * 输出: [-2,0,3,5,3]
     *
     * 初始状态:
     * [0,0,0,0,0]
     * 进行了操作 [1,3,2] 后的状态:[0,2,2,2,0]
     * 进行了操作 [2,4,3] 后的状态:[0,2,5,5,3]
     * 进行了操作 [0,2,2] 后的状态:[-2,0,3,5,3]
     */

    int[] getModifiedArray(int length, int[][] updates) {
        // nums 初始化为全 0
        int[] nums = new int[length];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] update : updates) {
            int i = update[0];
            int j = update[1];
            int val = update[2];
            df.increment(i, j, val);
        }

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
