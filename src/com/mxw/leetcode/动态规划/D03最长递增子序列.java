package com.mxw.leetcode.动态规划;

import java.util.Arrays;

public class D03最长递增子序列 {
    /**
     给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

     输入：nums = [10,9,2,5,3,7,101,18]
     输出：4
     解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

     以假设 dp[0...i-1] 都已经被算出来了，然后问自己：怎么通过这些结果算出 dp[i]？
     dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度。

     我们就可以推出 base case：dp[i] 初始值为 1，因为以 nums[i] 结尾的最长递增子序列起码要包含它自己。
     我们的最终结果（子序列的最大长度）应该是 dp 数组中的最大值。

     假设我们已经知道了 dp[0..4] 的所有结果，我们如何通过这些已知结果推出 dp[5] 呢？
     刚才我们对 dp 数组的定义，现在想求 dp[5] 的值，也就是想求以 nums[5] 为结尾的最长递增子序列。
     nums[5] = 3，既然是递增子序列，我们只要找到前面那些结尾比 3 小的子序列，然后把 3 接到这些子序列末尾，就可以形成一个新的递增子序列，而且这个新的子序列长度加一。
     nums[5] 前面有哪些元素小于 nums[5]？这个好算，用 for 循环比较一波就能把这些元素找出来。

     nums[0] 和 nums[4] 都是小于 nums[5] 的，然后对比 dp[0] 和 dp[4] 的值，我们让 nums[5] 和更长的递增子序列结合，得出 dp[5] = 3：
     */

    int lengthOfLIS(int[] nums) {
        // 定义：dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度
        int[] dp = new int[nums.length];

        // base case：dp 数组全都初始化为 1
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;

    }
}
