package com.mxw.leetcode.贪心算法区间;

import java.util.Arrays;

public class T05跳跃游戏2 {

    /**
     给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     0 <= j <= nums[i]
     i + j < n
     返回到达nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。

     输入: nums = [2,3,1,1,4]
     输出: 2
     解释: 跳到最后一个位置的最小跳跃数是 2。
         从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。

     dp 函数：
     // 定义：从索引 p 跳到最后一格，至少需要 dp(nums, p) 步
     int dp(int[] nums, int p);
     */


    int[] memo;
    // 主函数
    public int jump(int[] nums) {
        int n = nums.length;
        // 备忘录都初始化为 n，相当于 INT_MAX
        // 因为从 0 跳到 n - 1 最多 n - 1 步
        memo = new int[n];
        Arrays.fill(memo, n);

        return dp(nums, 0);
    }
    // 定义：从索引 p 跳到最后一格，至少需要 dp(nums, p) 步
    int dp(int[] nums, int p) {
        int n = nums.length;
        // base case
        if (p >= n - 1) {
            return 0;
        }
        // 子问题已经计算过
        if (memo[p] != n) {
            return memo[p];
        }
        // 当前步数
        int steps = nums[p];
        // 你可以选择跳 1 步，2 步...
        for (int i = 1; i <= steps; i++) {
            // 穷举每一个选择
            // 计算每一个子问题的结果 =>优化：这就是贪心选择性质，我们不需要「递归地」计算出所有选择的具体结果然后比较求最值，而只需要做出那个最有「潜力」，看起来最优的选择即可。
            int subProblem = dp(nums, p + i);
            // 取其中最小的作为最终结果
            memo[p] = Math.min(memo[p], subProblem + 1);
        }
        return memo[p];
    }

    /**
     贪心算法
     * @param nums
     * @return
     */
    int jumpV2(int[] nums) {
        int n = nums.length;
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(nums[i] + i, farthest);
            if (end == i) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }

}
