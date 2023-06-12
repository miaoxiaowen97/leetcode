package com.mxw.leetcode.二分搜索;

public class B03寻找右侧边界的二分搜索 {
    /**
     常见的二分搜索有时候并不能处理我们的问题。比如：给一个有序数组nums = [1,2,2,2,3]，target 为 2。此时要求我们找出第一个出现2的位置。
     但是我们按照常见的二分搜索找出的却是返回索引为2的。即为第三个位置，因为索引是从0开始的。我们如何查找左侧边界的目标呢？
     今天，我们将升级改造一下代码，并且跟常规的做对比，发现有哪些不一样。

     框架：
     int left_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 注意
        // 搜索区间为 [left, right]
        while (left <= right) { // 注意
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
            // 别返回，锁定左侧边界,此时有可能左侧还存在目标值，需要再次确认。
                 right = mid - 1;
            }else if (nums[mid] < target) {
             // 搜索区间变为 [mid+1, right]
                 left = mid + 1;
            }else if (nums[mid] > target) {
             // 搜索区间变为 [left, mid-1]
                 right = mid - 1; // 注意
            }
        }
        //注意
        // 判断 target 是否存在于 nums 中
        // 此时 target 比所有数都大，返回 -1
        if (left == nums.length) return -1;
        // 判断一下 nums[left] 是不是 target
        return nums[left] == target ? left : -1;
     }

     区别：
     1。if (nums[mid] == target)，不直接返回。此时有可能左侧还存在目标值，需要再次确认。
     2.循环遍历终止后，需要对左侧值进行判断。如果左侧值超出了数据长度，此时目标值不存在。返回-1
      如果存在，对比左侧对应的值是否与目标值匹配，如果匹配返回当前的左侧值的索引。不存在返回-1
     */

    /**
     实例调试。int[] nums={1,1,2,2,2,3,3,3,4,5};
     */
   public static int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 这里改成收缩左侧边界即可
                left = mid + 1;
            }
        }
        // 最后改成返回 left - 1
        if (left - 1 < 0 || left - 1 >= nums.length) {
            return -1;
        }
        return nums[left - 1] == target ? (left - 1) : -1;
    }

    public static void main(String[] args) {
        int[] nums={1,1,2,2,2,3,3,3,4,5};
        int i = right_bound(nums,3);
        System.out.println(i);
    }
}
