package com.mxw.leetcode.图;

public class T01图的遍历 {

    /**
     // 记录被遍历过的节点
     boolean[] visited;
     // 记录从起点到当前节点的路径
     boolean[] onPath

     void traverse(Graph graph, int s) {
         if (visited[s]) return;
         // 经过节点 s，标记为已遍历
         visited[s] = true;
         // 做选择：标记节点 s 在路径上
         onPath[s] = true;
         for (int neighbor : graph.neighbors(s)) {
            traverse(graph, neighbor);
         }
         // 撤销选择：节点 s 离开路径
         onPath[s] = false;
     }

     */
}
