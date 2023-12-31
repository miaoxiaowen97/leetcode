package com.mxw.leetcode.股票买卖;

public class A3买卖股票的最佳时机2 {

    /**
     *给你一个整数数组prices，其中prices[i]表示某支股票第i天的价格。
     *在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有一股股票。你也可以先购买，然后在同一天出售。
     *返回你能获得的最大利润
     *
     *输入：prices=[7,1,5,3,6,4]
     *输出：7
     *解释：在第2天（股票价格=1）的时候买入，在第3天（股票价格=5）的时候卖出,这笔交易所能获得利润=5-1=4。
     *随后，在第4天（股票价格=3）的时候买入，在第5天（股票价格=6）的时候卖出,这笔交易所能获得利润=6-3=3。
     *总利润为4+3=7。
     *
     *输入：prices=[7,6,4,3,1]
     *输出：0
     *解释：在这种情况下,交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为0。
     *
     * 特点：在每一天，你可以决定是否购买和/或出售股票：k是正无穷的情况。
     * 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的。
     *
     *我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     */
    int maxProfit_k_inf(int[] prices) {
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
