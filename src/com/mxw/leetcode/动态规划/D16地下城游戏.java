package com.mxw.leetcode.动态规划;

import java.util.Arrays;

public class D16地下城游戏 {
    /**
     入一个存储着整数的二维数组 grid，如果 grid[i][j] > 0，说明这个格子装着血瓶，经过它可以增加对应的生命值；
     如果 grid[i][j] == 0，则这是一个空格子，经过它不会发生任何事情；
     如果 grid[i][j] < 0，说明这个格子有怪物，经过它会损失对应的生命值。
     现在你是一名骑士，将会出现在最左上角，公主被困在最右下角，你只能向右和向下移动，请问你初始至少需要多少生命值才能成功救出公主？

     输入：dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
     输出：7
     -2   -3   3
     -5  -10   1
     10   30  -5
     解释：如果骑士遵循最佳路径：右 -> 右 -> 下 -> 下 ，则骑士的初始健康点数至少为7。
     */

    /**
     * 关键不在于吃最多的血瓶，而是在于如何损失最少的生命值
     * dp函数：int dp(int[][] grid, int i, int j);
     * 从grid[i][j] 到达终点（右下角）所需的最少生命值是 dp(grid, i, j)。
     * 状态转移方程：
     * int res = min(
     * dp(i + 1, j),
     * dp(i, j + 1)
     * ) - grid[i][j];
     * <p>
     * dp(i, j) = res <= 0 ? 1 : res;
     */

    int[][] memo;

    /**
     * 主函数
     */
    public int calculateMinimumHP(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 备忘录中都初始化为 -1
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(grid, 0, 0);
    }

    /** 定义：从 (i, j) 到达右下角，需要的初始血量至少是多少 */
    int dp(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        // base case
        if (i == m - 1 && j == n - 1) {
            return grid[i][j] >= 0 ? 1 : -grid[i][j] + 1;
        }
        if (i == m || j == n) {
            return Integer.MAX_VALUE;
        }
        // 避免重复计算
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 状态转移逻辑
        int res = Math.min(
                dp(grid, i, j + 1),
                dp(grid, i + 1, j)
        ) - grid[i][j];
        // 骑士的生命值至少为 1
        memo[i][j] = res <= 0 ? 1 : res;
        return memo[i][j];
    }

    public int calculateMinimumHPV3(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] < 0 ? -dungeon[m - 1][n - 1] + 1 : 1;
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == m || j == n) {
                    dp[i][j] = Integer.MAX_VALUE;
                    continue;
                }
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                int res = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                dp[i][j] = res <= 0 ? 1 : res;
            }
        }
        return dp[0][0];
    }

}
