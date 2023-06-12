package com.mxw.leetcode.股票买卖;

public class A5买卖股票的最佳时机4 {

    /**
     给定一个整数数组prices，其中prices[i]表示第i天的股票价格 ；整数fee代表了交易股票的手续费用。

     你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

     返回获得利润的最大值。

     注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

     输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
     输出：8
     解释：能够达到的最大利润:
     在此处买入prices[0] = 1
     在此处卖出 prices[3] = 8
     在此处买入 prices[4] = 4
     在此处卖出 prices[5] = 9
     总利润:((8 - 1) - 2) + ((9 - 4) - 2) = 8

     每次交易要支付手续费，只要把手续费从利润中减去即可
     dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
     解释：相当于买入股票的价格升高了。
     在第一个式子里减也是一样的，相当于卖出股票的价格减小了。

     */
    int maxProfit_with_fee(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // base case
                dp[i][0] = 0;
                //   dp[i][1]
                // = max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee)
                // = max(dp[-1][1], dp[-1][0] - prices[i] - fee)
                // = max(-inf, 0 - prices[i] - fee)
                // = -prices[i] - fee
                dp[i][1] = -prices[i] - fee;
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }

}
