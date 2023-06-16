package com.mxw.leetcode.排序;

import java.util.*;

public class A2归并排序_计算右侧小于当前元素的个数 {

    /**
     给你一个整数数组 nums ，按要求返回一个新数组counts 。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于nums[i] 的元素的数量。

     输入：nums = [5,2,6,1]
     输出：[2,1,1,0]
     解释：
     5 的右侧有 2 个更小的元素 (2 和 1)
     2 的右侧仅有 1 个更小的元素 (1)
     6 的右侧有 1 个更小的元素 (1)
     1 的右侧有 0 个更小的元素

     我们在使用 merge 函数合并两个有序数组的时候，其实是可以知道一个元素 nums[i] 后边有多少个元素比 nums[i] 小的。
     在对 nums[lo..hi] 合并的过程中，每当执行 nums[p] = temp[i] 时，就可以确定 temp[i] 这个元素后面比它小的元素个数为 j - mid - 1。

     */

    private static class Pair {
        int val, id;
        Pair(int val, int id) {
            // 记录数组的元素值
            this.val = val;
            // 记录元素在数组中的原始索引
            this.id = id;
        }
    }

    // 归并排序所用的辅助数组
    private static Pair[] temp;
    // 记录每个元素后面比自己小的元素个数
    private static int[] count;

    // 主函数
    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        temp = new Pair[n];
        Pair[] arr = new Pair[n];
        // 记录元素原始的索引位置，以便在 count 数组中更新结果
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }

        // 执行归并排序，本题结果被记录在 count 数组中
        sort(arr, 0, n - 1);

        List<Integer> res = new LinkedList<>();
        for (int c : count) {
            res.add(c);
        }
        return res;
    }

    // 归并排序
    private static void sort(Pair[] arr, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    // 合并两个有序数组
    private static void merge(Pair[] arr, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = arr[i];
        }

        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                arr[p] = temp[j++];
            } else if (j == hi + 1) {
                // 右半边数组已全部被合并
                arr[p] = temp[i++];
                // 更新 count 数组
                count[arr[p].id] += j - mid - 1;
            } else if (temp[i].val > temp[j].val) {
                arr[p] = temp[j++];
            } else {
                arr[p] = temp[i++];
                // 更新 count 数组
                count[arr[p].id] += j - mid - 1;
            }
        }
    }

    public static void main(String[] args) {
        int [] nums = {5,2,6,1};
        List<Integer> list = countSmaller(nums);
    }
}
