package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class T14寻找重复的子树 {

    /**
     * 给你一棵二叉树的根节点 root ，返回所有 重复的子树 。
     * 对于同一类的重复子树，你只需要返回其中任意 一棵 的根结点即可。
     * 如果两棵树具有 相同的结构 和 相同的结点值 ，则认为二者是 重复 的。
     * <p>
     * 输入：root = [1,2,3,4,null,2,4,null,null,4]
     * 输出：[[2,4],[4]]
     * <p>
     * <p>
     *      1
     *   /     \
     *  2      3
     * /      /   \
     * 4      2     4
     *      /
     *     4
     * <p>
     * <p>
     * 1、以我为根的这棵二叉树（子树）长啥样？
     * 知道以自己为根的子树长啥样，是不是得先知道我的左右子树长啥样 =>后序遍历
     * 2、以其他节点为根的子树都长啥样？
     * 将自己序列化存储，结合map判断是否跟自己一样
     */

    // 记录所有子树以及出现的次数
    HashMap<String, Integer> memo = new HashMap<>();
    // 记录重复的子树根节点
    LinkedList<TreeNode> res = new LinkedList<>();

    /* 主函数 */
    List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    /* 辅助函数 */
    String traverse(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + "," + right + "," + root.val;

        int freq = memo.getOrDefault(subTree, 0);

        // 多次重复也只会被加入结果集一次
        if (freq == 1) {
            res.add(root);
        }
        // 给子树对应的出现次数加一
        memo.put(subTree, freq + 1);
        return subTree;
    }


}
