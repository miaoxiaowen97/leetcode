package com.mxw.leetcode.二分搜索;

public class B01寻找一个数 {

    /**
     * 最常用的二分查找场景：寻找一个数、寻找左侧边界、寻找右侧边界
     *
     查找框架：
      int binarySearch(int[] nums, int target) {
         // 左边界，有边界
          int left = 0, right = ...;
          while(...) {
            //计算 mid 时需要防止溢出，代码中 left + (right - left) / 2 就和 (left + right) / 2 的结果相同，
            //但是有效防止了 left 和 right 太大，直接相加导致溢出的情况。
            int mid = left + (right - left) / 2;
             if (nums[mid] == target) {
                 ...
             } else if (nums[mid] < target) {
                 left = ...
             } else if (nums[mid] > target) {
                 right = ...
             }
          }
      }
     ...标记的地方，可能出现细节问题的地方。

     */

    /**
      题目描述：
      给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1
      输入: nums = [-1,0,3,5,9,12], target = 9
      输出: 4
      解释: 9 出现在 nums 中并且下标为 4
     */

    int binarySearch(int[] nums, int target) {
        int left = 0;
        // 注意,决定了后续循环遍历的条件,
        // (nums.length - 1)相当于两端都闭区间 [left, right]，nums.length相当于左闭右开区间 [left, right)，
        // 因为索引大小为 nums.length 是越界的。
        //这个算法中使用的是前者 [left, right] 两端都闭的区间。这个区间其实就是每次进行搜索的区间。
        int right = nums.length - 1;
        // 初始化 right 的赋值是 nums.length - 1，即最后一个元素的索引，而不是 nums.length
        // while(left <= right) 的终止条件是 left == right + 1，写成区间的形式就是 [right + 1, right]，或者带个具体的数字进去 [3, 2]，
        // 可见这时候区间为空，因为没有数字既大于等于 3 又小于等于 2 的吧。所以这时候 while 循环终止是正确的，直接返回 -1 即可。
        //while(left < right) 的终止条件是 left == right，写成区间的形式就是 [right, right]，或者带个具体的数字进去 [2, 2]，这时候区间非空，还有一个数 2，
        // 但此时 while 循环终止了。也就是说这区间 [2, 2] 被漏掉了，索引 2 没有被搜索，如果这时候直接返回 -1 就是错误的。
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                //找到了目标值可以终止
                return mid;
            } else if (nums[mid] < target) {
                // 注意
                // 确定下一步的搜索区间。
                //本算法的搜索区间是两端都闭的，即 [left, right]。那么当我们发现索引 mid 不是要找的 target 时，下一步应该去搜索哪里呢？
                //因为 mid 已经搜索过，应该从搜索区间中去除,去搜索区间 [left, mid-1] 或者区间 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 注意
                right = mid - 1;
            }
        }
        // while 循环终止，然后返回 -1
        return -1;
    }

}
