package com.mxw.leetcode.回溯算法;

import java.util.LinkedList;
import java.util.List;

public class H03无重复元素不可重复选择排列 {

    /**
     /**
     *  回溯算法的「排列树」
     *              .
     *      1/     2|     \3  树枝：可供的选择
     *      .       .(站点) .
     *   2/  \3  1/  \3  1/  \2
     *   .    .   .   .   .   .
     *   |    |   |   |   |    |
     *   3    2   3   1   2    3
     *
     *
     *  回溯算法的「组合树」组合为3的结果
     *                 .
     *      1/        2|       \3  树枝：可供的选择
     *     [1]        [2]      [3]
     *   2/   \3      |3           因为集合中的元素不用考虑顺序， [1,2,3] 中 2 后面只有 3，如果你向前考虑 1，那么 [2,1] 会和之前已经生成的子集 [1,2] 重复。
     * [1,2] [1,3]   [2,3]
     *   |3
     * [1,2,3]
     */

    /**
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     */

    List<List<Integer>> res = new LinkedList<>();

    // 记录回溯算法的递归路径
    LinkedList<Integer> track = new LinkedList<>();

    // 主函数
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    // 回溯算法核心函数，遍历子集问题的回溯树
    void backtrack(int[] nums, int start) {
        // 前序位置，每个节点的值都是一个子集
        res.add(new LinkedList<>(track));

        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);
            // 通过 start 参数控制树枝的遍历，避免产生重复的子集
            backtrack(nums, i + 1);
            // 撤销选择
            track.removeLast();
        }
    }

}
