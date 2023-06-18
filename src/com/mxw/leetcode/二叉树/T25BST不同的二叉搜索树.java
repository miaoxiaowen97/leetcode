package com.mxw.leetcode.二叉树;

public class T25BST不同的二叉搜索树 {

    /**
     *
     1、对于 BST 的每一个节点 node，左子树节点的值都比 node 的值要小，右子树节点的值都比 node 的值大。
     2、对于 BST 的每一个节点 node，它的左侧子树和右侧子树都是 BST。
     BST 的中序遍历结果是有序的（升序）

     给你输入一个正整数n，请你计算，存储{1,2,3...,n}这些值共有有多少种不同的 BST 结构。
     输入n = 3，算法返回 5，因为共有如下 5 种不同的 BST 结构存储{1,2,3}：

     1           3          3        2       1
     \          /           /      /   \      \
      3        2           1       1   3       2
     /        /            \                    \
     2       1              2                    3
     */

    // 备忘录
    int[][] memo;

    int numTrees(int n) {
        // 备忘录的值初始化为 0
        memo = new int[n + 1][n + 1];
        return count(1, n);
    }

    int count(int lo, int hi) {
        if (lo > hi) {
            return 1;
        }
        // 查备忘录
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }
        int res = 0;
        for (int mid = lo; mid <= hi; mid++) {
            int left = count(lo, mid - 1);
            int right = count(mid + 1, hi);
            res += left * right;
        }
        // 将结果存入备忘录
        memo[lo][hi] = res;
        return res;
    }

}
