package com.mxw.leetcode.数组双指针;

/**
 * @author miao
 */
public class A07两数之和 {

    /**
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
     * <p>
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     */

    public int[] twoSum(int[] numbers, int target) {
        // 一左一右两个指针相向而行
        int left = 0, right = numbers.length - 1;

        while (left<right){
            int sum=numbers[left]+numbers[right];
            if (sum==target){
                // 找到符合的两个数
                return new int[]{left+1,right+1};
            }else if (sum<target){
                left++;
            }else if (sum>target){
                right--;
            }
        }

        return new int[]{-1,-1};

    }
}
