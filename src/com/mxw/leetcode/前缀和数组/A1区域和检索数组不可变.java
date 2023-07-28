package com.mxw.leetcode.前缀和数组;

public class A1区域和检索数组不可变 {

    /**
     给定一个整数数组 nums，处理以下类型的多个查询:

     计算索引left和right（包含 left 和 right）之间的 nums 元素的和 ，其中left <= right
     实现 NumArray 类：

     NumArray(int[] nums) 使用数组 nums 初始化对象
     int sumRange(int i, int j) 返回数组 nums中索引left和right之间的元素的总和，
     包含left和right两点（也就是nums[left] + nums[left + 1] + ... + nums[right])

     输入：
     ["NumArray", "sumRange", "sumRange", "sumRange"]
     [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
     输出：
     [null, 1, -1, -3]

     解释：
     NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
     numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
     numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1)) 
     numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))

     
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
