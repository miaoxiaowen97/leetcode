 package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;
import org.w3c.dom.Node;

 public class T08填充节点的右侧指针 {

    /**

             4
          /   \
         2     7
        / \   / \
       1   3 6   9
     把二叉树的每一层节点都用 next 指针连接起来
            4->null
          /   \
         7 -> 2->null
        / \   / \
       9->6->3 ->1->null

     难点：不同父节点的节点怎么链接，比如6->3

     */

    // 主函数
    TreeNode connect(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 遍历「三叉树」，连接相邻节点
        traverse(root.left, root.right);
        return root;
    }

     // 三叉树遍历框架
     void traverse(TreeNode node1, TreeNode node2) {
         if (node1 == null || node2 == null) {
             return;
         }
         /**** 前序位置 ****/
         // 将传入的两个节点穿起来
         node1.next = node2;
         // 连接相同父节点的两个子节点
         traverse(node1.left, node1.right);
         traverse(node2.left, node2.right);
         // 连接跨越父节点的两个子节点
         traverse(node1.right, node2.left);
     }

}
