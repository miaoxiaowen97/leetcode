package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

public class T30BTS二叉搜索树的最近公共祖先 {
    /**
     但对于 BST 来说，根本不需要老老实实去遍历子树，由于 BST 左小右大的性质，将当前节点的值与val1和val2作对比即可判断当前节点是不是LCA：
     假设val1 < val2，那么val1 <= root.val <= val2则说明当前节点就是LCA；若root.val比val1还小，
     则需要去值更大的右子树寻找LCA；若root.val比val2还大，则需要去值更小的左子树寻找LCA。
     */
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 保证 val1 较小，val2 较大
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        return find(root, min, max);
    }
    // 在 BST 中寻找 val1 和 val2 的最近公共祖先节点
    TreeNode find(TreeNode root, int min, int max) {
        if (root == null) {
            return null;
        }
        if (root.val > max) {
            // 当前节点太大，去左子树找
            return find(root.left, min, max);
        }
        if (root.val < min) {
            // 当前节点太小，去右子树找
            return find(root.right, min, max);
        }
        // val1 <= root.val <= val2
        // 则当前节点就是最近公共祖先
        return root;
    }
}
