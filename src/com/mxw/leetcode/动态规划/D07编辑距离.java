package com.mxw.leetcode.动态规划;

import java.util.Arrays;

public class D07编辑距离 {

    /**
     给你两个单词word1和word2， 请返回将word1转换成word2 所使用的最少操作数 。
     你可以对一个单词进行如下三种操作：
     插入一个字符
     删除一个字符
     替换一个字符

     输入：word1 = "horse", word2 = "ros"
     输出：3
     解释：
     horse -> rorse (将 'h' 替换为 'r')
     rorse -> rose (删除 'r')
     rose -> ros (删除 'e')

     双指针：
     if s1[i] == s2[j]:
         啥都别做（skip）
         i, j 同时向前移动
     else:
         三选一：
         插入（insert）
         删除（delete）
         替换（replace）
     */

    int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // i，j 初始化指向最后一个索引
        return dp(s1, m - 1, s2, n - 1);
    }

    // 定义：返回 s1[0..i] 和 s2[0..j] 的最小编辑距离
    int dp(String s1, int i, String s2, int j) {
        // base case
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            // 啥都不做
            /**
             # 本来就相等，不需要任何操作
             # s1[0..i] 和 s2[0..j] 的最小编辑距离等于
             # s1[0..i-1] 和 s2[0..j-1] 的最小编辑距离
             # 也就是说 dp(i, j) 等于 dp(i-1, j-1)
             */
            return dp(s1, i - 1, s2, j - 1);
        }

        /**
         dp(s1, i, s2, j - 1) + 1,    # 插入
         # 解释：
         # 我直接在 s1[i] 插入一个和 s2[j] 一样的字符
         # 那么 s2[j] 就被匹配了，前移 j，继续跟 i 对比
         # 别忘了操作数加一
         dp(s1, i - 1, s2, j) + 1,    # 删除
         # 解释：
         # 我直接把 s[i] 这个字符删掉
         # 前移 i，继续跟 j 对比
         # 操作数加一
         dp(s1, i - 1, s2, j - 1) + 1 # 替换
         # 解释：
         # 我直接把 s1[i] 替换成 s2[j]，这样它俩就匹配了
         # 同时前移 i，j 继续对比
         # 操作数加一
         */
        int min = min(
                dp(s1, i, s2, j - 1) + 1,    // 插入
                dp(s1, i - 1, s2, j) + 1,    // 删除
                dp(s1, i - 1, s2, j - 1) + 1 // 替换
        );

        return min;

    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    // 备忘录
    int[][] memo;

    public int minDistanceV2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 备忘录初始化为特殊值，代表还未计算
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp2(s1, m - 1, s2, n - 1);
    }

    int dp2(String s1, int i, String s2, int j) {
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }
        // 查备忘录，避免重叠子问题
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 状态转移，结果存入备忘录
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp(s1, i - 1, s2, j - 1);
        } else {
            memo[i][j] =  min(
                    dp(s1, i, s2, j - 1) + 1,
                    dp(s1, i - 1, s2, j) + 1,
                    dp(s1, i - 1, s2, j - 1) + 1
            );
        }
        return memo[i][j];
    }

    /**
     处理两个字符串的动态规划问题，都是按本文的思路处理，建立 DP table。为什么呢，因为易于找出状态转移的关系，比如编辑距离的 DP table：

     dp 数组和递归 dp 函数含义一样，也就可以直接套用之前的思路写代码，唯一不同的是，DP table 是自底向上求解，递归解法是自顶向下求解：
     */
    int minDistancev3(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 定义：s1[0..i] 和 s2[0..j] 的最小编辑距离是 dp[i+1][j+1]
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        // 自底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }

        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }
}
