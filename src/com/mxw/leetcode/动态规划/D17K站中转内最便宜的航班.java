package com.mxw.leetcode.动态规划;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class D17K站中转内最便宜的航班 {

    /**
     有n个城市通过一些航班连接。给你一个数组flights ，其中flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi开始，以价格 pricei 抵达 toi。
     现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。
     如果不存在这样的路线，则输出 -1。

     输入:
     n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     src = 0, dst = 2, k = 1
     输出: 200
     解释:
     城市航班图如下
             0
         /100   \ 500
        1   --   2
            100

     从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。

     */

    /**
     dp 函数：int dp(int s, int k);
     从起点 src 出发，k 步之内（一步就是一条边）到达节点 s 的最小路径权重为 dp(s, k)。
     */

    // 哈希表记录每个点的入度
    // to -> [from, price]
    HashMap<Integer, List<int[]>> indegree;
    int src, dst;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // 将中转站个数转化成边的条数 k = 1=》k = 2；一个站对应两条边
        K++;
        this.src = src;
        this.dst = dst;
        indegree = new HashMap<>();

        for (int[] f : flights) {
            int from = f[0];
            int to = f[1];
            int price = f[2];
            // 记录谁指向该节点，以及之间的权重
            indegree.putIfAbsent(to, new LinkedList<>());
            indegree.get(to).add(new int[] {from, price});
        }
        return dp(dst, K);
    }

    // 定义：从 src 出发，k 步之内到达 s 的最短路径权重
    int dp(int s, int k) {
        // base case
        if (s == src) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }
        // 初始化为最大值，方便等会取最小值
        int res = Integer.MAX_VALUE;

        if (indegree.containsKey(s)) {
            // 当 s 有入度节点时，分解为子问题
            for (int[] v : indegree.get(s)) {
                int from = v[0];
                int price = v[1];
                // 从 src 到达相邻的入度节点所需的最短路径权重
                int subProblem = dp(from, k - 1);
                // 跳过无解的情况
                if (subProblem != -1) {
                    res = Math.min(res, subProblem + price);
                }
            }
        }
        // 如果还是初始值，说明此节点不可达
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    // 备忘录
    int[][] memo;

    public int findCheapestPriceV2(int n, int[][] flights, int src, int dst, int K) {
        K++;
        this.src = src;
        this.dst = dst;
        // 初始化备忘录，全部填一个特殊值
        memo = new int[n][K + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -888);
        }

        indegree = new HashMap<>();

        for (int[] f : flights) {
            int from = f[0];
            int to = f[1];
            int price = f[2];
            // 记录谁指向该节点，以及之间的权重
            indegree.putIfAbsent(to, new LinkedList<>());
            indegree.get(to).add(new int[] {from, price});
        }

        return dpV2(dst, K);
    }

    // 定义：从 src 出发，k 步之内到达 s 的最小成本
    int dpV2(int s, int k) {
        // base case
        if (s == src) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }
        // 查备忘录，防止冗余计算
        if (memo[s][k] != -888) {
            return memo[s][k];
        }

        // 状态转移不变
        // 初始化为最大值，方便等会取最小值
        int res = Integer.MAX_VALUE;

        if (indegree.containsKey(s)) {
            // 当 s 有入度节点时，分解为子问题
            for (int[] v : indegree.get(s)) {
                int from = v[0];
                int price = v[1];
                // 从 src 到达相邻的入度节点所需的最短路径权重
                int subProblem = dp(from, k - 1);
                // 跳过无解的情况
                if (subProblem != -1) {
                    res = Math.min(res, subProblem + price);
                }
            }
        }

        // 存入备忘录
        memo[s][k] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[s][k];
    }
}
