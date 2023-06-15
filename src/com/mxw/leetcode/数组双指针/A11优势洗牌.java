package com.mxw.leetcode.数组双指针;

import java.util.Arrays;
import java.util.PriorityQueue;

public class A11优势洗牌 {
    /**
     给定两个长度相等的数组nums1和nums2，nums1相对于 nums2 的优势可以用满足nums1[i] > nums2[i]的索引 i的数目来描述。
     返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。

     输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
     输出：[2,11,7,15]

     int n = nums1.length;

     sort(nums1); // 田忌的马
     sort(nums2); // 齐王的马

     // 从最大的开始比
     for (int i = n - 1; i >= 0; i--) {
         if (nums1[i] > nums2[i]) {
             // 比得过，跟他比
         } else {
            // 比不过，换个垫底的来送人头
         }
     }
     */

    public static int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 给 nums2 降序排序
        // 给 nums2 降序排序
        PriorityQueue<int[]> maxpq = new PriorityQueue<>(
                (int[] pair1, int[] pair2) -> {
                    return pair2[1] - pair1[1];
                }
        );
        for (int i = 0; i < n; i++) {
            maxpq.offer(new int[]{i, nums2[i]});
        }
        //[[0,1],[2,4],[1,10],[3,11]]

        // 给 nums1 升序排序
        Arrays.sort(nums1);
        // nums1[left] 是最小值，nums1[right] 是最大值
        int left = 0, right = n - 1;
        int[] res = new int[n];

        while (!maxpq.isEmpty()) {
            int[] pair = maxpq.poll();
            // maxval 是 nums2 中的最大值，i 是对应索引
            int i = pair[0], maxval = pair[1];
            if (maxval < nums1[right]) {
                // 如果 nums1[right] 能胜过 maxval，那就自己上
                res[i] = nums1[right];
                right--;
            }else {
                // 否则用最小值混一下，养精蓄锐
                res[i] = nums1[left];
                left++;
            }

        }

        return res;
    }

    public static void main(String[] args) {
       int [] nums1 = {2,7,11,15}, nums2 = {1,10,4,11};
        advantageCount(nums1,nums2);
    }
}
