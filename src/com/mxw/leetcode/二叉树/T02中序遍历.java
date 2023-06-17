package com.mxw.leetcode.二叉树;

public class T02中序遍历 {

    /**
     * 遍历模板
     * void traverse(TreeNode root) {
     *     if (root == null) {
     *         return;
     *     }
     *     // 前序位置
     *     traverse(root.left);
     *     // 中序位置
     *     traverse(root.right);
     *     // 后序位置
     * }
     *
     * 一般说二叉树的遍历框架都是指递归的形式。
     * 只要是递归形式的遍历，都可以有前序位置和后序位置，分别在递归之前和递归之后。
     *
     * 前中后序是遍历二叉树过程中处理每一个节点的三个特殊时间点，绝不仅仅是三个顺序不同的 List：
     * 前序位置的代码在刚刚进入一个二叉树节点的时候执行；
     * 后序位置的代码在将要离开一个二叉树节点的时候执行；
     * 中序位置的代码在一个二叉树节点左子树都遍历完，即将开始遍历右子树的时候执行。
     *
     * void traverse(TreeNode root) {
     *     if (root == null) return;
     *     printf("从节点 %s 进入节点 %s", root, root.left);
     *     traverse(root.left);
     *     printf("从节点 %s 回到节点 %s", root.left, root);
     *
     *     printf("从节点 %s 进入节点 %s", root, root.right);
     *     traverse(root.right);
     *     printf("从节点 %s 回到节点 %s", root.right, root);
     * }
     */

    // 中序位置主要用在 BST 场景中，你完全可以把 BST 的中序遍历认为是遍历有序数组。

}
