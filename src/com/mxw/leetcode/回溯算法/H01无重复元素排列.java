package com.mxw.leetcode.回溯算法;

import java.util.LinkedList;
import java.util.List;

public class H01无重复元素排列 {

    /**
     *回溯算法是在遍历「树枝」=》树枝：选择
     *
     * 需要思考 3 个问题：
     * 1、路径：也就是已经做出的选择。
     * 2、选择列表：也就是你当前可以做的选择。
     * 3、结束条件：也就是到达决策树底层，无法再做选择的条件。
     *
     * 回溯算法的框架：
     * result = []
     * def backtrack(路径（已经做出的选择）, 选择列表):
     *    if 满足结束条件:
     *         result.add(路径)
     *         return
     *    for 选择 in 选择列表:
     *        if 不符合选择条件：
     *           continue
     *        做选择
     *        backtrack(路径, 选择列表)
     *        撤销选择
     */

    /**
     * 比如输入 nums = [1,2,3]，函数的返回值应该是：
     *      [
     *          [1,2,3],[1,3,2],
     *          [2,1,3],[2,3,1],
     *          [3,1,2],[3,2,1]
     *      ]
     * 回溯算法的「决策树」
     *              .
     *      1/     2|     \3  树枝：可供的选择
     *      .       .(站点) .
     *   2/  \3  1/  \3  1/  \2
     *   .    .   .   .   .   .
     *   |    |   |   |   |    |
     *   3    2   3   1   2    3
     *
     *   站点 ：[2] 就是「路径」，记录你已经做过的选择；[1,3] 就是「选择列表」，表示你当前可以做出的选择；
     *   「结束条件」就是遍历到树的底层叶子节点，这里也就是选择列表为空的时候。
     *
     *
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
