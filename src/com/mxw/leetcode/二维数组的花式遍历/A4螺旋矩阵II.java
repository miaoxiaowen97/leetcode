package com.mxw.leetcode.二维数组的花式遍历;

import java.util.LinkedList;
import java.util.List;

public class A4螺旋矩阵II {

    /**
      给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。


                       [1,2,3]
         123456789=>   [8,9,4]
                       [7,6,5]

      按照右、下、左、上的顺序遍历数组，并使用四个变量圈定未遍历元素的边界：
      随着螺旋遍历，相应的边界会收缩，直到螺旋遍历完整个数组

     输入：n = 3
     输出：[[1,2,3],[8,9,4],[7,6,5]]
     */

    int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int upper_bound = 0, lower_bound = n - 1;
        int left_bound = 0, right_bound = n - 1;
        // 需要填入矩阵的数字
        int num = 1;
        while (num <= n * n) {
            if (upper_bound <= lower_bound) {
                // 在顶部从左向右遍历
                for (int j = left_bound; j <= right_bound; j++) {
                    matrix[upper_bound][j] = num++;
                }
                // 上边界下移
                upper_bound++;
            }

            if (left_bound <= right_bound) {
                // 在右侧从上向下遍历
                for (int i = upper_bound; i <= lower_bound; i++) {
                    matrix[i][right_bound] = num++;
                }
                // 右边界左移
                right_bound--;
            }

            if (upper_bound <= lower_bound) {
                // 在底部从右向左遍历
                for (int j = right_bound; j >= left_bound; j--) {
                    matrix[lower_bound][j] = num++;
                }
                // 下边界上移
                lower_bound--;
            }

            if (left_bound <= right_bound) {
                // 在左侧从下向上遍历
                for (int i = lower_bound; i >= upper_bound; i--) {
                    matrix[i][left_bound] = num++;
                }
                // 左边界右移
                left_bound++;
            }
        }
        return matrix;
    }
}

