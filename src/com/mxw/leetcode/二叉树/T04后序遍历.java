package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

public class T04后序遍历 {
    /**
     意味着前序位置的代码只能从函数参数中获取父节点传递来的数据，而后序位置的代码不仅可以获取参数数据，还可以获取到子树通过函数返回值传递回来的数据。
     和前序遍历的区别：
     1、如果把根节点看做第 1 层，如何打印出每一个节点所在的层数？：前序遍历
     2、如何打印出每个节点的左右子树各有多少节点？：后序遍历

     这两个问题的根本区别在于：一个节点在第几层，你从根节点遍历过来的过程就能顺带记录，用递归函数的参数就能传递下去；
     而以一个节点为根的整棵子树有多少个节点，你需要遍历完子树之后才能数清楚，然后通过递归函数的返回值拿到答案。
     一旦你发现题目和子树有关，那大概率要给函数设置合理的定义和返回值，在后序位置写代码了。

     // 二叉树遍历函数
     void traverse(TreeNode root, int level) {
         if (root == null) {
            return;
         }
         // 前序位置
         printf("节点 %s 在第 %d 层", root, level);
         traverse(root.left, level + 1);
         traverse(root.right, level + 1);
     }

     // 定义：输入一棵二叉树，返回这棵二叉树的节点总数
     int count(TreeNode root) {
         if (root == null) {
            return 0;
         }
         int leftCount = count(root.left);
         int rightCount = count(root.right);
         // 后序位置
         printf("节点 %s 的左子树有 %d 个节点，右子树有 %d 个节点",
         root, leftCount, rightCount);

         return leftCount + rightCount + 1;
     }
     */

    /**
     二叉树的直径

     给你一棵二叉树的根节点，返回该树的直径 。
     二叉树的直径 是指树中任意两个节点之间最长路径的长度 。这条路径可能经过也可能不经过根节点 root 。
     两节点之间路径的 长度 由它们之间边数表示。

     输入：root = [1,2,3,4,5]
     输出：3
     解释：
              1
            /   \
           2      3
         /   \
        4     5
     3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度

     每一条二叉树的「直径」长度，就是一个节点的左右子树的最大深度之和
     */

    // 记录最大直径的长度
    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return maxDiameter;
    }

    int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax=maxDepth(root.left);
        int rightMax=maxDepth(root.right);

        // 后序位置，顺便计算最大直径
        int myDiameter = leftMax + rightMax;
        maxDiameter = Math.max(maxDiameter, myDiameter);
        return 1 + Math.max(leftMax, rightMax);
    }

}
