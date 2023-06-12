package com.mxw.leetcode.nSum问题;

import java.util.Arrays;

public class A1twoSum {

    /**
     * 如果假设输入一个数组 nums 和一个目标和 target，请你返回 nums 中能够凑出 target 的两个元素的值，
     * 比如输入 nums = [1,3,5,6], target = 9，那么算法返回两个元素 [3,6]。可以假设只有且仅有一对儿元素可以凑出 target。
     *
     *
     */

    public int[] twoSum(int[] nums, int target) {
        // 先对数组排序
        Arrays.sort(nums);
        // 左右指针
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            // 根据 sum 和 target 的比较，移动左右指针
            if (sum < target) {
                lo++;
            } else if (sum > target) {
                hi--;
            } else if (sum == target) {
                return new int[]{nums[lo], nums[hi]};
            }
        }
        return new int[]{};
    }
}
