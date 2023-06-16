package com.mxw.leetcode.排序;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class 快速排序_寻找第k个最大的元素 {

    /**
     我们寻找第 k 个最大的元素，稍微有点绕，意思是去寻找 nums 数组降序排列后排名第 k 的那个元素。

     比如输入 nums = [2,1,5,4], k = 2，算法应该返回 4，因为 4 是 nums 中第 2 个最大的元素。
     }

     快速排序的过程是一个构造二叉搜索树的过程。
     */

    int findKthLargestV1(int[] nums, int k) {
        // 小顶堆，堆顶是最小元素
        PriorityQueue<Integer>
                pq = new PriorityQueue<>();
        for (int e : nums) {
            // 每个元素都要过一遍二叉堆
            pq.offer(e);
            // 堆中元素多于 k 个时，删除堆顶元素
            if (pq.size() > k) {
                pq.poll();
            }
        }
        // pq 中剩下的是 nums 中 k 个最大元素，
        // 堆顶是最小的那个，即第 k 个最大元素
        return pq.peek();
    }

    int findKthLargestV2(int[] nums, int k) {
        // 首先随机打乱数组
        shuffle(nums);
        int lo = 0, hi = nums.length - 1;
        // 转化成「排名第 k 的元素」
        k = nums.length - k;
        while (lo <= hi) {
            // 在 nums[lo..hi] 中选一个分界点
            int p = partition(nums, lo, hi);
            if (p < k) {
                // 第 k 大的元素在 nums[p+1..hi] 中
                lo = p + 1;
            } else if (p > k) {
                // 第 k 大的元素在 nums[lo..p-1] 中
                hi = p - 1;
            } else {
                // 找到第 k 大元素
                return nums[p];
            }
        }
        return -1;
    }


    // 对 nums[lo..hi] 进行切分
    int partition(int[] nums, int lo, int hi) {
        // 见前文
        return 1;
    }

    // 洗牌算法，将输入的数组随机打乱
    void shuffle(int[] nums) {
        // 见前文
    }

    // 原地交换数组中的两个元素
    void swap(int[] nums, int i, int j) {
        // 见前文
    }

    public static void main(String[] args) {

    }
}
