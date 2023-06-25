package com.mxw.leetcode.图.entity;

public class Vertex {
    int id;
    Vertex[] neighbors;

    /**
     如果用代码的形式来表现，邻接表和邻接矩阵大概长这样
     // 邻接表:好处是占用的空间少,无法快速判断两个节点是否相邻
     邻接表的使用会更频繁一些，主要是因为操作起来较为简单
     // graph[x] 存储 x 的所有邻居节点
     List<Integer>[] graph;

     // 邻接矩阵
     // matrix[x][y] 记录 x 是否有一条指向 y 的边
     boolean[][] matrix;

     度：每个节点相连的边的条数。
     节点 3 的入度为 3（有三条边指向它），出度为 1（它有 1 条边指向别的节点）

     有向加权图：不仅仅存储某个节点 x 的所有邻居节点，还存储 x 到每个邻居的权重
     // 邻接表
     // graph[x] 存储 x 的所有邻居节点以及对应的权重
     List<int[]>[] graph;
     // 邻接矩阵
     // matrix[x][y] 记录 x 指向 y 的边的权重，0 表示不相邻
     int[][] matrix;
     */
}
