package com.mxw.leetcode.回溯算法;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class H08重复元素不可重复选择全排列 {
    /**
     *
     * 给你输入一个可包含重复数字的序列 nums，请你写一个算法，返回所有可能的全排列
     * 比如输入 nums = [1,2,2]，函数返回：
     * [ [1,2,2],[2,1,2],[2,2,1] ]
     *
     * 假设输入为 nums = [1,2,2']，标准的全排列算法会得出如下答案：
     * [
     *     [1,2,2'],[1,2',2],
     *     [2,1,2'],[2,2',1],
     *     [2',1,2],[2',2,1]
     * ]
     *
     * 重复：[1,2,2']和[1,2',2], [2,1,2']和[2',1,2]，[2,2',1]和[2',2,1]。
     * 去重：保证相同元素在排列中的相对位置保持不变。比如说 nums = [1,2,2'] 这个例子，我保持排列中 2 一直在 2' 前面。
     * 这样的话，你从上面 6 个排列中只能挑出 3 个排列符合这个条件：
     * [ [1,2,2'],[2,1,2'],[2,2',1] ]
     *
     *
     */

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    void backtrack(int[] nums) {
        if (track.size() == nums.length) {
            //长度为三的时候才加入
            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            // 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                // 如果前面的相邻相等元素没有用过，则跳过
                //当出现重复元素时，比如输入 nums = [1,2,2',2'']，2'只有在 2 已经被使用的情况下才会被选择，
                // 同理，2'' 只有在 2' 已经被使用的情况下才会被选择，这就保证了相同元素在排列中的相对位置保证固定。
                continue;
            }

            track.add(nums[i]);
            used[i] = true;
            backtrack(nums);
            track.removeLast();
            used[i] = false;

        }

    }

}
