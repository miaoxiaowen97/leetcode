package com.mxw.leetcode.二叉树;

import com.mxw.leetcode.二叉树.entity.TreeNode;

import java.util.LinkedList;

public class T15后序遍历二叉树序列化 {

    /**
     *
     如果你的序列化结果中不包含空指针的信息，且你只给出一种遍历顺序，那么你无法还原出唯一的一棵二叉树。

     如果你的序列化结果中不包含空指针的信息，且你会给出两种遍历顺序，那么按照前文 东哥手把手带你刷二叉树（构造篇） 所说，分两种情况：
     2.1. 如果你给出的是前序和中序，或者后序和中序，那么你可以还原出唯一的一棵二叉树。
     2.2. 如果你给出前序和后序，那么你无法还原出唯一的一棵二叉树。

     如果你的序列化结果中包含空指针的信息，且你只给出一种遍历顺序，也要分两种情况：
     3.1. 如果你给出的是前序或者后序，那么你可以还原出唯一的一棵二叉树。
     3.2. 如果你给出的是中序，那么你无法还原出唯一的一棵二叉树。
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
    /* 辅助函数，将二叉树存入 StringBuilder */
    void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        serialize(root.left, sb);
        serialize(root.right, sb);

        /****** 后序遍历位置 ******/
        sb.append(root.val).append(SEP);
        /***********************/
    }

    /**
     反序列化过程也是一样，先确定根节点 root，然后遵循前序遍历的规则，递归生成左右子树即可：
     String data = "1,2,#,4,#,#,3,#,#";
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
