package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

import java.util.HashMap;

public class T12通过后序和中序遍历结果构造二叉树 {

    /**
     给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历，
     postorder 是同一棵树的后序遍历，请你构造并返回这颗二叉树


     输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     输出：[3,9,20,null,null,15,7]

          3
       /     \
     9      20
           /   \
           15   7

      肯定要想办法确定根节点的值，把根节点做出来，然后递归构造左右子树即可。
     */

    // 存储 inorder 中值到索引的映射
    HashMap<Integer, Integer> valToIndex = new HashMap<>();

    TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }


    /*
     build 函数的定义：
     后序遍历数组为 postorder[postStart..postEnd]，
     中序遍历数组为 inorder[inStart..inEnd]，
     构造二叉树，返回该二叉树的根节点
 */
    TreeNode build(int[] inorder, int inStart, int inEnd,
                   int[] postorder, int postStart, int postEnd) {

        if (inStart > inEnd) {
            return null;
        }
        // root 节点对应的值就是后序遍历数组的最后一个元素
        int rootVal = postorder[postEnd];
        // rootVal 在中序遍历数组中的索引
        int index = valToIndex.get(rootVal);
        // 左子树的节点个数
        int leftSize = index - inStart;
        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = build(inorder, inStart, index - 1,
                postorder, postStart, postStart + leftSize - 1);

        root.right = build(inorder, index + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1);

        return root;

    }

}
