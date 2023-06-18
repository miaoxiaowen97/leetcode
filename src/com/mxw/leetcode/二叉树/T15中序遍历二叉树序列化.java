package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

import java.util.LinkedList;

public class T15中序遍历二叉树序列化 {

    /**
     *
     中序遍历的方式行不通，因为无法实现反序列化方法 deserialize
     */

    String SEP = ",";
    String NULL = "#";

    /* 主函数，将二叉树序列化为字符串 */
    String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    /* 辅助函数，将二叉树存入 StringBuilder */
    void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        serialize(root.left, sb);
        /****** 中序遍历位置 ******/
        sb.append(root.val).append(SEP);
        /***********************/
        serialize(root.right, sb);
    }

    /**
     反序列化过程也是一样，先确定根节点 root，然后遵循前序遍历的规则，递归生成左右子树即可：
     首先要构造 root 节点。前序遍历得到的 nodes 列表中，第一个元素是 root 节点的值；后序遍历得到的 nodes 列表中，最后一个元素是 root 节点的值。
     中序遍历的代码，root 的值被夹在两棵子树的中间，也就是在 nodes 列表的中间，我们不知道确切的索引位置，所以无法找到 root 节点，也就无法进行反序列化。
     */
    /* 主函数，将字符串反序列化为二叉树结构 */
    TreeNode deserialize(String data) {
        // 将字符串转化成列表
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            nodes.addLast(s);
        }
        return deserialize(nodes);
    }

    /* 辅助函数，通过 nodes 列表构造二叉树 */
    TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }

        /****** 前序遍历位置 ******/
        // 列表最左侧就是根节点
        String first = nodes.removeFirst();
        if (first.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        /***********************/

        root.left = deserialize(nodes);
        root.right = deserialize(nodes);

        return root;
    }

}
