package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class T20BST寻找第K小的元素 {

    /**
     *
     1、对于 BST 的每一个节点 node，左子树节点的值都比 node 的值要小，右子树节点的值都比 node 的值大。
     2、对于 BST 的每一个节点 node，它的左侧子树和右侧子树都是 BST。
     BST 的中序遍历结果是有序的（升序）

     给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     输入：root = [3,1,4,null,2], k = 1
     输出：1
     一个直接的思路就是升序排序，然后找第 k 个元素呗。BST 的中序遍历其实就是升序排序的结果，找第 k 个元素肯定不是什么难事
     */
    int kthSmallest(TreeNode root, int k) {
        // 利用 BST 的中序遍历特性
        traverse(root, k);
        return res;
    }

    // 记录结果
    int res = 0;
    // 记录当前元素的排名
    int rank = 0;

    void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        /* 中序遍历代码位置 */
        rank++;
        if (k == rank) {
            // 找到第 k 小的元素
            res = root.val;
            return;
        }
        /*****************/
        traverse(root.right, k);
    }
}
