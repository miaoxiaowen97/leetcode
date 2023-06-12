package com.mxw.leetcode.BFS;

import com.mxw.leetcode.二叉树.entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class B01二叉树的最小高度 {
    /**
     * BFS:
     * 在一幅「图」中找到从起点 start 到终点 target 的最近距离
     *
     * 框架:
     * // 计算从起点 start 到终点 target 的最近距离
     * int BFS(Node start, Node target) {
     *      Queue<Node> q; // 核心数据结构
     *      Set<Node> visited; // 避免走回头路
     *
     *      q.offer(start); // 将起点加入队列
     *      visited.add(start);
     *      int step = 0; // 记录扩散的步数
     *
     *      while (q not empty) {  // 从起点开始遍历
     *        int sz = q.size(); // 队列的大小为当前节点可以扩散的位置
     *        // 将当前队列中的所有节点向四周扩散
     *        for (int i = 0; i < sz; i++) {
     *            // 当前所在的节点
     *            Node cur = q.poll();
     *            // 划重点：这里判断是否到达终点
     *            if (cur is target){
     *                //记录扩散的步数
     *                return step;
     *            }
     *         // 将 cur 的相邻节点加入队列
     *         for (Node x : cur.adj()) {
     *              if (x not in visited) {
     *                  q.offer(x);
     *                  visited.add(x);
     *              }
     *          }
     *        }
     *         // 划重点：更新步数在这里
     *          step++;
     *      }
     * }
     */

    /**
     *
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明：叶子节点是指没有子节点的节点
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * =》
     *         3
     *      /    \
     *     9     20
     *          /  \
     *         15   7
     * 输出：2
     *
     * 起点就是 root 根节点，终点就是最靠近根节点的那个「叶子节点」嘛，叶子节点就是两个子节点都是 null 的节点：
     * 照我们上述的框架稍加改造来写解法
     */
    int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        //注意这个 while 循环和 for 循环的配合，while 循环控制一层一层往下走，for 循环利用 sz 变量控制从左到右遍历每一层二叉树节点：
        while (!q.isEmpty()) {
            int sz = q.size();

            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                /* 判断是否到达终点 */
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                /* 将 cur 的相邻节点加入队列 */
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            /* 这里增加步数 */
            depth++;
        }
        return depth;
    }
}
