package com.mxw.leetcode.打家劫舍;

import com.mxw.leetcode.二叉树.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class A3打家劫舍3 {

    /**
     小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
     除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。

     输入: root = [3,2,3,null,3,null,1]
     输出: 7
     解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7

     输入: root = [3,4,5,1,3,null,1]
     输出: 9
     解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9

     */

    Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 利用备忘录消除重叠子问题
        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        // 抢，然后去下下家
        int do_it = root.val
                + (root.left == null ?
                0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ?
                0 : rob(root.right.left) + rob(root.right.right));

        // 不抢，然后去下家
        int not_do = rob(root.left) + rob(root.right);

        int res = Math.max(do_it, not_do);
        memo.put(root, res);
        return res;
    }


}
