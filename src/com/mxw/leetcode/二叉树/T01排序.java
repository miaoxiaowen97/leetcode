package com.mxw.leetcode.二叉树;

public class T01排序 {
    /**
     *快速排序的逻辑是，若要对 nums[lo..hi] 进行排序，我们先找一个分界点 p，通过交换元素使得 nums[lo..p-1] 都小于等于 nums[p]，
     *且 nums[p+1..hi] 都大于 nums[p]，然后递归地去 nums[lo..p-1] 和 nums[p+1..hi] 中寻找新的分界点，最后整个数组就被排序了。
     * 快速排序的代码框架如下：
     * void sort(int[] nums, int lo, int hi) {
     *     通过交换元素构建分界点p
     *     int p = partition(nums, lo, hi);
     *
     *     sort(nums, lo, p - 1);
     *     sort(nums, p + 1, hi);
     * }
     */

    /**
     * 归并排序的逻辑:nums[lo..hi] 进行排序，我们先对 nums[lo..mid] 排序，再对 nums[mid+1..hi] 排序，最后把这两个有序的子数组合并，整个数组就排好序
     *归并排序的代码框架如下：
     * void sort(int[] nums, int lo, int hi) {
     *     int mid = (lo + hi) / 2;
     *
     *     排序 nums[lo..mid]
     *     sort(nums, lo, mid);
     *     排序 nums[mid+1..hi]
     *     sort(nums, mid + 1, hi);
     *
     *     合并 nums[lo..mid] 和 nums[mid+1..hi]
     *     merge(nums, lo, mid, hi);
     * }
     */
}
