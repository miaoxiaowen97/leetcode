package com.mxw.leetcode.回溯算法;

import java.util.LinkedList;
import java.util.List;

public class H05无重复元素不可重复选择全排列 {

    /**
     *给定一个不含重复数字的数组 nums，返回其所有可能的全排列。
     * 比如输入 nums = [1,2,3]，函数的返回值应该是：
     *      [
     *          [1,2,3],[1,3,2],
     *          [2,1,3],[2,3,1],
     *          [3,1,2],[3,2,1]
     *      ]
     *
     *  回溯算法的「组合树」组合为3的结果
     *                   .
     *      1/          2|             \3  树枝：可供的选择
     *     [1]          [2]            [3]
     *   2/   \3       |1   \3        1/   \ 2
     * [1,2]  [1,3]   [2,1]  [2,3]  [3,1]  [3,2]
     *   |3      |      |      |      |     |
     * [1,2,3][1,3,2][2,1,3][2,3,1][3,1,2][3,2,1]
     *
     * 用 used 数组标记已经在路径上的元素避免重复选择，然后收集所有叶子节点上的值，就是所有全排列的结果：
     */

    List<List<Integer>> res = new LinkedList<>();
    // 记录回溯算法的递归路径
    LinkedList<Integer> track = new LinkedList<>();

    // track 中的元素会被标记为 true
    boolean[] used;

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    // 回溯算法核心函数
    void backtrack(int[] nums) {
        // base case，到达叶子节点
        if (track.size() == nums.length) {
            // 收集叶子节点上的值
            res.add(new LinkedList(track));
            return;
        }
        // 回溯算法标准框架
        for (int i = 0; i < nums.length; i++) {
            // 已经存在 track 中的元素，不能重复选择
            if (used[i]) {
                continue;
            }
            // 做选择
            used[i] = true;
            track.addLast(nums[i]);
            // 进入下一层回溯树
            backtrack(nums);
            // 取消选择
            track.removeLast();
            used[i] = false;
        }
    }

}
