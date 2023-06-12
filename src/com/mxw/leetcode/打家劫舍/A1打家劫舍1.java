package com.mxw.leetcode.打家劫舍;

import java.util.Arrays;

public class A1打家劫舍1 {

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     * <p>
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     * <p>
     * 从左到右走过这一排房子，在每间房子前都有两种选择：抢或者不抢。
     * 状态：每户人家
     * 选择：抢？不抢？
     * 最优解res：max(当前不抢，抢下一家；当前抢，抢下下家)
     * 可能存在重叠子问题。比如访问num[3]=》num[1]选择抢；num[2]选择不抢，都会重复访问num[3]
     * 可以用备忘录进行优化：int[] memo
     */
    private int[] memo;

    /**
     * 自顶向下的动态规划解法
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        // 初始化备忘录
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        // 强盗从第 0 间房子开始抢劫
        return dp(nums, 0);
    }

    // 返回 dp[start..] 能抢到的最大值
    private int dp(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }
        // 避免重复计算
        if (memo[start] != -1) {
            return memo[start];
        }
        int res = Math.max(dp(nums, start + 1),
                nums[start] + dp(nums, start + 2));
        // 记入备忘录
        memo[start] = res;
        return res;
    }

    /**
     * 自底向上
     * @param nums
     * @return
     */
   public static int rob2(int[] nums) {
           int n = nums.length;
           // dp[i] = x 表示：
           // 从第 i 间房子开始抢劫，最多能抢到的钱为 x
           // base case: dp[n] = 0
           int[] dp = new int[n + 2];
           for (int i = n - 1; i >= 0; i--) {
               dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
           }
           return dp[0];
    }

    public static void main(String[] args) {
        int i = rob3(new int[]{1,2,3,1});
        System.out.println(i);
    }

    public static int rob3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }
        int N = nums.length;
        int[] dp = new int[N+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int k = 2; k <= N; k++) {
            // 抢了上一家，和当前家加上两家的价值
            // 当前从2开始，nums[k-1]=nums[1],从第二家开始
            dp[k] = Math.max(dp[k-1], nums[k-1] + dp[k-2]);
        }
        return dp[N];
    }

    int rob4(int[] nums) {
        int n = nums.length;
        // 记录 dp[i+1] 和 dp[i+2]
        int dp_i_1 = 0, dp_i_2 = 0;
        // 记录 dp[i]
        int dp_i = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }

}
