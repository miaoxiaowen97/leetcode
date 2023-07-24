package com.mxw.leetcode.动态规划;

import java.util.Arrays;

public class D15最小路径和 {

    /**
     给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     说明：每次只能向下或者向右移动一步。

     输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     输出：7
     1  3  1
     1  5  1
     4  2  1
     解释：因为路径 1→3→1→1→1 的总和最小。
     让你在二维矩阵中求最优化问题（最大值或者最小值），肯定需要递归 + 备忘录，也就是动态规划技巧
     dp 函数的定义：int dp(int[][] grid, int i, int j);
     从左上角位置 (0, 0) 走到位置 (i, j) 的最小路径和为 dp(grid, i, j)。
     */

    int minPathSumV1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 计算从左上角走到右下角的最小路径和
        return dpV1(grid, m - 1, n - 1);
    }

    private int dpV1(int[][] grid, int i, int j) {
        // base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        // 如果索引出界，返回一个很大的值，
        // 保证在取 min 的时候不会被取到
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        // 左边和上面的最小路径和加上 grid[i][j]
        // 就是到达 (i, j) 的最小路径和
        int i1 = dpV1(grid, i - 1, j);
        int j1 = dpV1(grid, i, j - 1);
        return Math.min(i1, j1)+grid[i][j];
    }

    /**
     备忘录技巧进行优化
     */
    int[][] memo;

    int minPathSumV2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 构造备忘录，初始值全部设为 -1
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dpV2(grid, m - 1, n - 1);
    }

    int dpV2(int[][] grid, int i, int j) {

        // base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        // 避免重复计算
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // 将计算结果记入备忘录
        // 左边和上面的最小路径和加上 grid[i][j]
        // 就是到达 (i, j) 的最小路径和
        int i1 = dpV2(grid, i - 1, j);
        int j1 = dpV2(grid, i, j - 1);
        int min = Math.min(i1, j1) + grid[i][j];
        memo[i][j] =min;
        return memo[i][j];
    }

    /**
     二维 dp 数组: 左上角位置 (0, 0) 走到位置 (i, j) 的最小路径和为 dp[i][j]

     */

    int minPathSumV3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        /**** base case ****/
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int i1 = dp[i - 1][j];
                int j1 = dp[i][j - 1];
                int min = Math.min(i1, j1) + grid[i][j];
                dp[i][j] = min;
            }
        }
        return dp[m - 1][n - 1];
    }

}
