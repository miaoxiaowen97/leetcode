package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

public class T22BST在BST中搜索元素 {

    /**
     *
     1、对于 BST 的每一个节点 node，左子树节点的值都比 node 的值要小，右子树节点的值都比 node 的值大。
     2、对于 BST 的每一个节点 node，它的左侧子树和右侧子树都是 BST。
     BST 的中序遍历结果是有序的（升序）

     给定二叉搜索树（BST）的根节点root和一个整数值val。

     你需要在 BST 中找到节点值等于val的节点。 返回以该节点为根的子树。 如果节点不存在，则返回null。

     输入：root = [4,2,7,1,3], val = 2
     输出：[2,1,3]
           4
       /     \
       2       7
     /   \
     1    3


          2
        /   \
       1    3
     */
    TreeNode searchBST(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        // 去左子树搜索
        if (root.val > target) {
            return searchBST(root.left, target);
        }
        // 去右子树搜索
        if (root.val < target) {
            return searchBST(root.right, target);
        }
        return root;
    }
}
