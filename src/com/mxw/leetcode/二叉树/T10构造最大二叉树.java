 package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

 public class T10构造最大二叉树 {

    /**
     给定一个不重复的整数数组nums 。最大二叉树可以用下面的算法从nums 递归地构建:
     创建一个根节点，其值为nums 中的最大值。
     递归地在最大值左边的子数组前缀上构建左子树。
     递归地在最大值 右边 的子数组后缀上构建右子树。
     返回nums 构建的 最大二叉树 。

     输入：nums = [3,2,1,6,0,5]
     输出：[6,3,5,null,2,0,null,null,1]

             6
          /    \
         3      5
         \     /
          2   0
           \
            1
     递归调用如下所示：
      [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
      [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1]
      [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1]
     [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 []
     空数组，无子节点

     遍历数组把找到最大值 maxVal，从而把根节点 root 做出来，然后对 maxVal 左边的数组和右边的数组进行递归构建，作为 root 的左右子树。

     */

    /* 主函数 */
    TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

     // 定义：将 nums[lo..hi] 构造成符合条件的树，返回根节点
     TreeNode build(int[] nums, int lo, int hi) {
         // base case
         if (lo > hi) {
             return null;
         }
         // 找到数组中的最大值和对应的索引
         int index = -1, maxVal = Integer.MIN_VALUE;
         for (int i = lo; i <= hi; i++) {
             if (maxVal < nums[i]) {
                 index = i;
                 maxVal = nums[i];
             }
         }
         // 先构造出根节点
         TreeNode root = new TreeNode(maxVal);
         // 递归调用构造左右子树
         root.left = build(nums, lo, index - 1);
         root.right = build(nums, index + 1, hi);

         return root;
     }

 }
