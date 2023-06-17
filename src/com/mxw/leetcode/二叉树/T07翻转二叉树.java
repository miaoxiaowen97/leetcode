package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

public class T07翻转二叉树 {

    /**
             4
          /   \
         2     7
        / \   / \
       1   3 6   9

            4
          /   \
         7     2
        / \   / \
       9   6 3   1

     这题能不能用「遍历」的思维模式解决？
     可以，我写一个 traverse 函数遍历每个节点，让每个节点的左右子节点颠倒过来就行了。
     */
    // 主函数
    TreeNode invertTree(TreeNode root) {
        // 遍历二叉树，交换每个节点的子节点
        traverse(root);
        return root;
    }

    // 二叉树遍历函数
    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        /**** 前序位置 ****/
        // 每一个节点需要做的事就是交换它的左右子节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 遍历框架，去遍历左右子树的节点
        traverse(root.left);
        traverse(root.right);
    }

    /**
      invertTree(x.left) 先把 x 的左子树翻转，再用 invertTree(x.right) 把 x 的右子树翻转，
     最后把 x 的左右子树交换，这恰好完成了以 x 为根的整棵二叉树的翻转，即完成了 invertTree(x) 的定义。
     分解为子问题。定义：将以 root 为根的这棵二叉树翻转，返回翻转后的二叉树的根节点
     */


    TreeNode invertTreeV2(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 利用函数定义，先翻转左右子树
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // 然后交换左右子节点
        root.left = right;
        root.right = left;

        // 和定义逻辑自恰：以 root 为根的这棵二叉树已经被翻转，返回 root
        return root;
    }

}
