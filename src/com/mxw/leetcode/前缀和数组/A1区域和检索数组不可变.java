package com.mxw.leetcode.前缀和数组;

public class A1区域和检索数组不可变 {

    /**
     * 给定一个整数数组 nums，处理以下类型的多个查询:
     * 计算索引left和right（包含 left 和 right）之间的 nums 元素的 和 ，其中left <= right
     */
    // 前缀和数组
    private int[] preSum;

    /* 输入一个数组，构造前缀和 */
    public A1区域和检索数组不可变(int[] nums) {
        // preSum[0] = 0，便于计算累加和
        preSum = new int[nums.length + 1];
        // 计算 nums 的累加和
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }

    /* 查询闭区间 [left, right] 的累加和 */
    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }
}
