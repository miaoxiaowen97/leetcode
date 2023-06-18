package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

public class T05二叉树寻找一个元素 {
    /**
     给你输入一棵没有重复元素的二叉树根节点root和一个目标值val，请你写一个函数寻找树中值为val的节点。
     */
    // 定义：在以 root 为根的二叉树中寻找值为 val 的节点
    TreeNode find(TreeNode root, int val) {
        // base case
        if (root == null) {
            return null;
        }
        // 看看 root.val 是不是要找的
        if (root.val == val) {
            return root;
        }
        // root 不是目标节点，那就去左子树找
        TreeNode left = find(root.left, val);
        if (left != null) {
            return left;
        }
        // 左子树找不着，那就去右子树找
        TreeNode right = find(root.right, val);
        if (right != null) {
            return right;
        }
        // 实在找不到了
        return null;
    }
}
