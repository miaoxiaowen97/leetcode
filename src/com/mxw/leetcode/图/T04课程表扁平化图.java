package com.mxw.leetcode.图;

import java.util.*;

public class T04课程表扁平化图 {

    /**
     现在你总共有 numCourses 门课需要选，记为0到numCourses - 1。给你一个数组prerequisites ，
     其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修bi 。

     例如，想要学习课程 0 ，你需要先完成课程1 ，我们用一个匹配来表示：[0,1] 。
     返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组

     输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
     输出：[0,2,1,3]
     解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     因此，一个正确的课程顺序是[0,1,2,3] 。另一个正确的排序是[0,2,1,3]


     依赖问题，首先想到的就是把问题转化成「有向图」这种数据结构，只要图中存在环，那就说明存在循环依赖。
     首先可以把课程看成「有向图」中的节点，节点编号分别是 0, 1, ..., numCourses-1，把课程之间的依赖关系看做节点之间的有向边。
     比如说必须修完课程 1 才能去修课程 3，那么就有一条有向边从节点 1 指向 3。
     如果发现这幅有向图中存在环，那就说明课程之间存在循环依赖，肯定没办法全部上完；反之，如果没有环，那么肯定能上完全部课程。
     首先可以写一个建图函数
     核心就是判断一幅有向图中是否存在环
     */

    // 记录后序遍历结果
    List<Integer> postorder = new ArrayList<>();
    // 记录是否存在环
    boolean hasCycle = false;
    boolean[] visited, onPath;

    // 主函数
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        System.out.println(Arrays.toString(Arrays.stream(graph).toArray()));
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        // 遍历图
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
            System.out.println(Arrays.toString(postorder.toArray()));
        }
        // 有环图无法进行拓扑排序
        if (hasCycle) {
            return new int[]{};
        }
        // 逆后序遍历结果即为拓扑排序结果
        Collections.reverse(postorder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }

    // 图遍历函数
    void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            // 发现环
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            return;
        }
        // 前序遍历位置
        onPath[s] = true;
        visited[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        // 后序遍历位置
        /*
        因为一个任务必须等到它依赖的所有任务都完成之后才能开始开始执行。

         */
        postorder.add(s);
        onPath[s] = false;
    }

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



}
