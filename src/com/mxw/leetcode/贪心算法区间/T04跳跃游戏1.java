package com.mxw.leetcode.贪心算法区间;

public class T04跳跃游戏1 {

    /**
     给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     数组中的每个元素代表你在该位置可以跳跃的最大长度。
     判断你是否能够到达最后一个下标

     输入：nums = [2,3,1,1,4]
     输出：true
     解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     */
   static boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = 0;
        for (int i = 0; i < n - 1; i++) {
            // 不断计算能跳到的最远距离
            farthest = Math.max(farthest, i + nums[i]);
            // 可能碰到了 0，卡住跳不动了
            if (farthest <= i) {
                return false;
            }
        }
        return farthest >= n - 1;
    }

    public static void main(String[] args) {
        int[] nums={2,3,1,1,4};
        canJump(nums);
    }
}
