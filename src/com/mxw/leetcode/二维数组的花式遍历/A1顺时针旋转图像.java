package com.mxw.leetcode.二维数组的花式遍历;

public class A1顺时针旋转图像 {

    /**
      给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
      你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

     [1,2,3]    [7,4,1]
     [4,5,6]    [8,5,2]
     [7,8,9]    [9,6,3]

     可以先将 n x n 矩阵 matrix 按照左上到右下的对角线进行镜像对称,再对矩阵的每一行进行反转：
     [1,2,3]    [9,6,3]     [3,6,9]
     [4,5,6]    [8,5,2]     [2,5,8]
     [7,8,9]    [7,4,1]     [1,4,7]

     */
    // 将二维矩阵原地顺时针旋转 90 度
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 先沿对角线镜像对称二维矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][n - i - 1];
                matrix[n - j - 1][n - i - 1] = temp;
            }
        }
        // 然后反转二维矩阵的每一行
        for (int[] row : matrix) {
            reverse(row);
        }
    }

    // 反转一维数组
    void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (j > i) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

}
