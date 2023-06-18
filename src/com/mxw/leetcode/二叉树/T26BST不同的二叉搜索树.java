package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class T26BST不同的二叉搜索树 {

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

     1、穷举root节点的所有可能。
     2、递归构造出左右子树的所有合法 BST。
     3、给root节点穷举所有左右子树的组合。
     */

    /* 主函数 */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        // 构造闭区间 [1, n] 组成的 BST
        return build(1, n);
    }
    /* 构造闭区间 [lo, hi] 组成的 BST */
    List<TreeNode> build(int lo, int hi) {
        List<TreeNode> res = new LinkedList<>();

        // base case
        if (lo > hi) {
            res.add(null);
            return res;
        }

        // 1、穷举 root 节点的所有可能。
        for (int i = lo; i <= hi; i++) {
            // 2、递归构造出左右子树的所有合法 BST。
            List<TreeNode> leftTree = build(lo, i - 1);
            List<TreeNode> rightTree = build(i + 1, hi);
            // 3、给 root 节点穷举所有左右子树的组合。
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    // i 作为根节点 root 的值
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
