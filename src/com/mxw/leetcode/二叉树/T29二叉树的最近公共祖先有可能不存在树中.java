package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

import java.util.HashSet;

public class T29二叉树的最近公共祖先有可能不存在树中 {
    /**
     p和q不一定存在于树中，所以你不能遇到一个目标值就直接返回，而应该对二叉树进行完全搜索（遍历每一个节点），
     如果发现p或q不存在于树中，那么是不存在LCA的。

     */
    // 用于记录 p 和 q 是否存在于二叉树中
    boolean foundP = false, foundQ = false;

    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = find(root, p.val, q.val);
        if (!foundP || !foundQ) {
            return null;
        }
        // p 和 q 都存在二叉树中，才有公共祖先
        return res;
    }
    // 在二叉树中寻找 val1 和 val2 的最近公共祖先节点
    TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }
        TreeNode left = find(root.left, val1, val2);
        TreeNode right = find(root.right, val1, val2);
        // 后序位置，判断当前节点是不是 LCA 节点
        if (left != null && right != null) {
            return root;
        }

        // 后序位置，判断当前节点是不是目标值
        if (root.val == val1 || root.val == val2) {
            // 找到了，记录一下
            if (root.val == val1) foundP = true;
            if (root.val == val2) foundQ = true;
            return root;
        }

        return left != null ? left : right;
    }
}
