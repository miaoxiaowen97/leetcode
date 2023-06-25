package com.mxw.leetcode.图;

import java.util.LinkedList;
import java.util.List;

public class T03课程表是否存在环 {

    /**
     你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
     在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，
     表示如果要学习课程ai 则 必须 先学习课程 bi 。

     例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

     输入：numCourses = 2, prerequisites = [[1,0]]
     输出：true
     解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

     依赖问题，首先想到的就是把问题转化成「有向图」这种数据结构，只要图中存在环，那就说明存在循环依赖。
     首先可以把课程看成「有向图」中的节点，节点编号分别是 0, 1, ..., numCourses-1，把课程之间的依赖关系看做节点之间的有向边。
     比如说必须修完课程 1 才能去修课程 3，那么就有一条有向边从节点 1 指向 3。
     如果发现这幅有向图中存在环，那就说明课程之间存在循环依赖，肯定没办法全部上完；反之，如果没有环，那么肯定能上完全部课程。
     首先可以写一个建图函数
     核心就是判断一幅有向图中是否存在环
     */

    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            // 添加一条从 from 指向 to 的有向边
            // 边的方向是「被依赖」关系，即修完课程 from 才能修课程 to
            graph[from].add(to);
        }
        return graph;
    }

    // 记录一次递归堆栈中的节点
    boolean[] onPath;
    // 记录遍历过的节点，防止走回头路
    boolean[] visited;
    // 记录图中是否有环
    boolean hasCycle = false;

    boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            // 遍历图中的所有节点
            traverse(graph, i);
        }
        // 只要没有循环依赖可以完成所有课程
        return !hasCycle;
    }

    void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            // 出现环
            hasCycle = true;
        }

        if (visited[s] || hasCycle) {
            // 如果已经找到了环，也不用再遍历了
            return;
        }
        // 前序代码位置
        visited[s] = true;
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        // 后序代码位置
        onPath[s] = false;
    }

}
