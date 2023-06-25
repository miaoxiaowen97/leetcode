package com.mxw.leetcode.动态规划;

public class D11背包问题 {

    /**
     N：数量
     W：重量
     dp[i][w] 表示：对于前 i 个物品（从 1 开始计数），当前背包的容量为 w 时，这种情况下可以装下的最大价值是 dp[i][w]。
     如果你没有把这第 i 个物品装入背包，那么很显然，最大价值 dp[i][w] 应该等于 dp[i-1][w]，继承之前的结果。
     如果你把这第 i 个物品装入了背包，那么 dp[i][w] 应该等于 val[i-1] + dp[i-1][w - wt[i-1]]。=》你如果选择将第 i 个物品装进背包，那么第 i 个物品的价值 val[i-1] 肯定就到手了，接下来你就要在剩余容量 w - wt[i-1] 的限制下，在前 i - 1 个物品中挑选，求最大价值，即 dp[i-1][w - wt[i-1]]。

     int[][] dp[N+1][W+1]
     dp[0][..] = 0
     dp[..][0] = 0

     for i in [1..N]:
     for w in [1..W]:
     dp[i][w] = max(
         把物品 i 装进背包,
         不把物品 i 装进背包
     )
     return dp[N][W]
     */
    int knapsack(int W, int N, int[] wt, int[] val) {
        // base case 已初始化
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (w - wt[i - 1] < 0) {
                    // 超重了，这种情况下只能选择不装入背包
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 装入或者不装入背包，择优
                    dp[i][w] = Math.max(
                            dp[i - 1][w - wt[i-1]] + val[i-1],
                            dp[i - 1][w]
                    );
                }
            }
        }

        return dp[N][W];
    }
}
