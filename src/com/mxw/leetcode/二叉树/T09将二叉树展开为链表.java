 package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

 public class T09将二叉树展开为链表 {

    /**

             4
          /   \
         2     7
        / \   / \
       1   3 6   9

     4->2->1->3->7->6->9

     对于一个节点 x，可以执行以下流程：

     1、先利用 flatten(x.left) 和 flatten(x.right) 将 x 的左右子树拉平。
       4
     /   \
     2    7
     \     \
      1     6
       \     \
        3     9
     2、将 x 的右子树接到左子树下方，然后将整个左子树作为右子树。
     4->2->1->3->7->6->9
     */

    // 主函数
    // 定义：输入节点 root，然后 root 为根的二叉树就会被拉平为一条链表
    void flatten(TreeNode root){
        // base case
        if (root == null) {
            return;
        }

        // 利用定义，把左右子树拉平
        flatten(root.left);
        flatten(root.right);

        /**** 后序遍历位置 ****/
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

}
