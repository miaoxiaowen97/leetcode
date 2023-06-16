package com.mxw.leetcode.排序;

import java.util.Arrays;
import java.util.Random;

public class 快速排序 {

    /**
      快速排序是先将一个元素排好序，然后再将剩下的元素排好序

     快速排序的核心无疑是 partition 函数， partition 函数的作用是在 nums[lo..hi] 中寻找一个切分点 p，
     通过交换元素使得 nums[lo..p-1] 都小于等于 nums[p]，且 nums[p+1..hi] 都大于 nums[p]
     partition 函数干的事情，其实就是把 nums[p] 这个元素排好序了。

     void sort(int[] nums, int lo, int hi) {
         if (lo >= hi) {
            return;
         }
         // 对 nums[lo..hi] 进行切分
         // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
         int p = partition(nums, lo, hi);
         // 去左右子数组进行切分
         sort(nums, lo, p - 1);
         sort(nums, p + 1, hi);
     }

     快速排序的过程是一个构造二叉搜索树的过程。
     */

    static class Quick {

        public static void sort(int[] nums) {
            // 为了避免出现耗时的极端情况(你每次运气都特别背，有一边的元素特别少的话)，先随机打乱
            //shuffle(nums);
            // 排序整个数组（原地修改）
            sort(nums, 0, nums.length - 1);
        }

        private static void sort(int[] nums, int lo, int hi) {
            if (lo >= hi) {
                return;
            }
            // 对 nums[lo..hi] 进行切分
            // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
            int p = partition(nums, lo, hi);

            sort(nums, lo, p - 1);
            sort(nums, p + 1, hi);
        }

        // 对 nums[lo..hi] 进行切分
        private static int partition(int[] nums, int lo, int hi) {
            int pivot = nums[lo];
            // 关于区间的边界控制需格外小心，稍有不慎就会出错
            // 我这里把 i, j 定义为开区间，同时定义：
            // [lo, i) <= pivot；(j, hi] > pivot
            // 之后都要正确维护这个边界区间的定义
            int i = lo + 1, j = hi;
            // 当 i > j 时结束循环，以保证区间 [lo, hi] 都被覆盖
            while (i <= j) {
                while (i < hi && nums[i] <= pivot) {
                    i++;
                    // 此 while 结束时恰好 nums[i] > pivot
                }
                while (j > lo && nums[j] > pivot) {
                    j--;
                    // 此 while 结束时恰好 nums[j] <= pivot
                }
                // 此时 [lo, i) <= pivot && (j, hi] > pivot

                if (i >= j) {
                    break;
                }
                swap(nums, i, j);
            }
            // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
            swap(nums, lo, j);
            return j;
        }

        // 洗牌算法，将输入的数组随机打乱
        private static void shuffle(int[] nums) {
            Random rand = new Random();
            int n = nums.length;
            for (int i = 0 ; i < n; i++) {
                // 生成 [i, n - 1] 的随机数
                int r = i + rand.nextInt(n - i);
                swap(nums, i, r);
            }
        }
        // 原地交换数组中的两个元素
        private static void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] values={6,7,8,2,3,9,10,4,5};
        Quick quick = new Quick();
        quick.sort(values);
        System.out.println(Arrays.toString(values));
    }
}
