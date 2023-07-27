package com.mxw.leetcode.二维数组的花式遍历;

import java.util.LinkedList;
import java.util.List;

public class A3矩阵的螺旋遍历 {

    /**
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素
     *
     *
     *  [1,2,3]
     *  [4,5,6]
     *  [7,8,9]
     *
     * 按照右、下、左、上的顺序遍历数组，并使用四个变量圈定未遍历元素的边界：
     * 随着螺旋遍历，相应的边界会收缩，直到螺旋遍历完整个数组
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     */

    List<Integer> spiralOrder(int[][] nums) {
        int line = nums.length, row = nums[0].length;

        int top_bound = 0, bottom_bound = line - 1;
        int left_bound = 0, right_bound = row - 1;

        List<Integer> res = new LinkedList<>();
        // res.size() == m * n 则遍历完整个数组
        while (res.size() < line * row) {

            if (top_bound <= bottom_bound) {
                // 在顶部从左向右遍历
                for (int i = left_bound; i <= right_bound; i++) {
                    res.add(nums[top_bound][i]);
                }
                // 上边界下移
                top_bound++;
            }

            if (left_bound <= right_bound) {
                // 在右侧从上向下遍历
                for (int i = top_bound; i <= bottom_bound; i++) {
                    res.add(nums[i][right_bound]);
                }
                // 右边界左移
                right_bound--;
            }

            if (top_bound <= bottom_bound) {
                // 在底部从右向左遍历
                for (int i = right_bound; i >= left_bound; i--) {
                    res.add(nums[bottom_bound][i]);
                }
                // 下边界上移
                bottom_bound--;
            }

            if (left_bound <= right_bound) {
                // 在左侧从下向上遍历
                for (int i = bottom_bound; i >= top_bound; i--) {
                    res.add(nums[i][left_bound]);
                }
                // 左边界右移
                left_bound++;
            }
        }
        return res;
    }
}

