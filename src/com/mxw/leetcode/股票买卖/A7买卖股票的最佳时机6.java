package com.mxw.leetcode.股票买卖;

public class A7买卖股票的最佳时机6 {

    /**
     * 给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格，和一个整型 k 。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 输入：k = 2, prices = [2,4,1]
     * 输出：2
     * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
     * <p>
     * 输入：k = 2, prices = [3,2,6,5,0,3]
     * 输出：7
     * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
     * <p>
     * 特点：最多可以完成 k 笔交易。交易次数 k 最多有多大呢
     * 一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 k 应该不超过 n/2，如果超过，就没有约束作用了，相当于 k 没有限制的情况，而这种情况是之前解决过的。
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int max_k=k;

        if (max_k > n / 2) {
            // 复用之前交易次数 k 没有限制的情况
            return maxProfit_k_inf(prices);
        }

        int[][][] dp = new int[n][max_k + 1][2];

        // k = 0 时的 base case
        for (int i = 0; i < n; i++) {
            dp[i][0][1] = Integer.MIN_VALUE;
            dp[i][0][0] = 0;
        }

        for (int i = 0; i < n; i++){
            for (int d = max_k; d >= 1; d--) {
                if (i - 1 == -1) {
                    // 处理 i = -1 时的 base case
                    dp[i][d][0] = 0;
                    dp[i][d][1] = -prices[i];
                    continue;
                }
                dp[i][d][0] = Math.max(dp[i-1][d][0], dp[i-1][d][1] + prices[i]);
                dp[i][d][1] = Math.max(dp[i-1][d][1], dp[i-1][d-1][0] - prices[i]);
            }
        }
        return dp[n - 1][max_k][0];
    }

    private int maxProfit_k_inf(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // base case
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

}
