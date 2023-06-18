package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

public class T22BST在BST中删除一个数 {

    /**
     *
     1、对于 BST 的每一个节点 node，左子树节点的值都比 node 的值要小，右子树节点的值都比 node 的值大。
     2、对于 BST 的每一个节点 node，它的左侧子树和右侧子树都是 BST。
     BST 的中序遍历结果是有序的（升序）

     找到目标节点了，比方说是节点 A，如何删除这个节点，这是难点。因为删除节点的同时不能破坏 BST 的性质。有三种情况
     情况 1：A 恰好是末端节点，两个子节点都为空，那么它可以当场去世了。
     情况 2：A 只有一个非空子节点，那么它要让这个孩子接替自己的位置。
     情况 3：A 有两个子节点，麻烦了，为了不破坏 BST 的性质，A 必须找到左子树中最大的那个节点，或者右子树中最小的那个节点来接替自己。我们以第二种方式讲解。


     */
    TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            // 这两个 if 把情况 1 和 2 都正确处理了
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // 处理情况 3
            // 获得右子树最小的节点
            TreeNode minNode = getMin(root.right);
            // 删除右子树最小的节点
            root.right = deleteNode(root.right, minNode.val);
            // 用右子树最小的节点替换 root 节点
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        }else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
