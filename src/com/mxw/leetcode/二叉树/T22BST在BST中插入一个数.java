package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

public class T22BST在BST中插入一个数 {

    /**
     *
     1、对于 BST 的每一个节点 node，左子树节点的值都比 node 的值要小，右子树节点的值都比 node 的值大。
     2、对于 BST 的每一个节点 node，它的左侧子树和右侧子树都是 BST。
     BST 的中序遍历结果是有序的（升序）
     */
    TreeNode insertIntoBST(TreeNode root, int val) {
        // 找到空位置插入新节点
        if (root == null) return new TreeNode(val);
        // if (root.val == val)
        //     BST 中一般不会插入已存在元素
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        return root;
    }
}
