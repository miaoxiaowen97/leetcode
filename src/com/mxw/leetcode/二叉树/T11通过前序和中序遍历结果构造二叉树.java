package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

import java.util.HashMap;

public class T11通过前序和中序遍历结果构造二叉树 {

    /**
     * 给定两个整数数组preorder 和 inorder，其中preorder 是二叉树的先序遍历，
     * inorder是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     * <p>
     * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * 输出: [3,9,20,null,null,15,7]
     *    3
     * /     \
     * 9      20
     *      /   \
     *      15   7
     * <p>
     * 肯定要想办法确定根节点的值，把根节点做出来，然后递归构造左右子树即可。
     */

    // 存储 inorder 中值到索引的映射
    HashMap<Integer, Integer> valToIndex = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }


    TreeNode build(int[] preorder, int preStart, int preEnd,
                   int[] inorder, int inStart, int inEnd) {

        if (preStart > preEnd) {
            return null;
        }
        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int index = valToIndex.get(rootVal);

        // 左子树的数量。比如3左边的子树只有一个9，索引：1-0=1
        int leftSize = index - inStart;
        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);

        return root;

    }

}
