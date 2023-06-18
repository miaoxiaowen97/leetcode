package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

public class T27二叉树的最近公共祖先 {
    /**
     给你输入一棵不含重复值的二叉树，以及存在于树中的两个节点p和q，请你计算p和q的最近公共祖先节点。

     如果一个节点能够在它的左右子树中分别找到p和q，则该节点为LCA节点
     */
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return find(root, p.val, q.val);
    }

    // 在二叉树中寻找 val1 和 val2 的最近公共祖先节点
    TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }
        // 前序位置
        if (root.val == val1 || root.val == val2) {
            // 如果遇到目标值，直接返回
            /**
             如果找到一个值为val1或val2的节点则直接返回，恰好解决了第二种情况：
             5是5和7的祖先
                   3
                 /   \
                5     1
               /  \  / \
             6   2 0   8
                /\
               7  4
             */
            return root;
        }
        TreeNode left = find(root.left, val1, val2);
        TreeNode right = find(root.right, val1, val2);

        // 后序位置，已经知道左右子树是否存在目标值
        if (left != null && right != null) {
            // 当前节点是 LCA 节点
            /**
             如果发现left和right都非空，就说明当前节点是LCA节点，即解决了第一种情况：6，7 的祖先是5
                   3
                 /   \
                5     1
               /  \  / \
              6   2 0   8
                 /\
                7  4
             */
            return root;
        }
        return left != null ? left : right;
    }
}
