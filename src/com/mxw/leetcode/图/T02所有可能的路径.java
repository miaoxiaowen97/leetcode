package com.mxw.leetcode.图;

import java.util.LinkedList;
import java.util.List;

public class T02所有可能的路径 {

    /**
     给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
     graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。

     输入：graph = [[1,2],[3],[3],[]]
     输出：[[0,1,3],[0,2,3]]
     解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3

     以 0 为起点遍历图，同时记录遍历过的路径，当遍历到终点时将路径记录下来即可
     既然输入的图是无环的，我们就不需要 visited 数组辅助了，直接套用图的遍历框架：

     */
    // 记录所有路径
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 维护递归过程中经过的路径
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }
    void traverse(int[][] graph, int s, LinkedList<Integer> path) {
        // 添加节点 s 到路径
        path.addLast(s);

        int n = graph.length;
        if (s == n - 1) {
            // 到达终点
            res.add(new LinkedList<>(path));
            // 可以在这直接 return，但要 removeLast 正确维护 path
            // path.removeLast();
            // return;
            // 不 return 也可以，因为图中不包含环，不会出现无限递归
        }
        // 递归每个相邻节点
        for (int v : graph[s]) {
            traverse(graph, v, path);
        }
        // 从路径移出节点 s
        path.removeLast();
    }
}
