package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

public class T06二叉树的最大深度 {

    // 记录最大深度
    int res = 0;
    // 记录遍历到的节点的深度
    int depth = 0;

    // 主函数
    int maxDepth(TreeNode root) {
        traverse(root);
        return res;
    }

    // 二叉树遍历框架
    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置:是进入一个节点的时候
        depth++;
        if (root.left == null && root.right == null) {
            // 到达叶子节点，更新最大深度
            // 放到前中后序位置都可以，只要保证在进入节点之后，离开节点之前（即 depth 自增之后，自减之前）就行了。
            res = Math.max(res, depth);
        }
        traverse(root.left);
        traverse(root.right);

        // 后序位置:是离开一个节点的时候
        depth--;
    }

    //分解问题计算答案
    // 定义：输入根节点，返回这棵二叉树的最大深度
    int maxDepthV2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 利用定义，计算左右子树的最大深度
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);

        // 整棵树的最大深度等于左右子树的最大深度取最大值，然后再加上根节点自己
        int res = Math.max(leftMax, rightMax) + 1;

        return res;
    }

}
