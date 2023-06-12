package com.mxw.leetcode.回溯算法;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class H06重复元素不可重复选择排列 {

    /**
     * 给你一个整数数组 nums，其中可能包含重复元素，请你返回该数组所有可能的子集。
     * 比如输入 nums = [1,2,2]，你应该输出：
     * [ [],[1],[2],[1,2],[2,2],[1,2,2] ]
     * =》以 nums = [1,2,2] 为例，为了区别两个 2 是不同元素，后面我们写作 nums = [1,2,2']。
     * =》[
     *          [],
     *          [1],[2],[2'],
     *          [1,2],[1,2'],[2,2'],
     *          [1,2,2']
     *      ]
     *=>产生重复：[2],[2'],[1,2],[1,2']
     *=>我们需要进行剪枝，如果一个节点有多条值相同的树枝相邻，则只遍历第一条，剩下的都剪掉，不要去遍历：
     *=>在代码上，需要先进行排序，让相同的元素靠在一起，如果发现 nums[i] == nums[i-1]，则跳过：
     *
     */

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        backtrack(nums, 0);
        return res;
    }

    void backtrack(int[] nums, int start) {
        // 前序位置，每个节点的值都是一个子集
        res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            // 剪枝逻辑，值相同的相邻树枝，只遍历第一条
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            track.addLast(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }

    }

}
