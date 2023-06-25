package com.mxw.leetcode.动态规划;

import java.util.Arrays;

public class D10最长的回文子序列 {

    /**
     给定一个字符串 s ，找到其中最长的回文子序列。可以假设 s 的最大长度为1000
     输入:
     "bbbab"
     输出: 4
     一个可能的最长回文子序列为"bbbb"。
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // dp 数组全部初始化为 0
        // dp 数组的定义是：在子串s[i..j]中，最长回文子序列的长度为dp[i][j]。
        int[][] dp = new int[n][n];
        // base case
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 反着遍历保证正确的状态转移
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j)) {
                    //如果它俩相等，那么它俩加上s[i+1..j-1]中的最长回文子序列就是s[i..j]的最长回文子序列
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    //它俩不可能同时出现在s[i..j]的最长回文子序列中，那么把它俩分别加入s[i+1..j-1]中，看看哪个子串产生的回文子序列更长即可
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        // 整个 s 的最长回文子串长度
        return dp[0][n - 1];
    }

}
