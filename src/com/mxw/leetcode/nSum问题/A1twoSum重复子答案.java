package com.mxw.leetcode.nSum问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A1twoSum重复子答案 {

    /**
     比如说输入为 nums = [1,3,1,2,2,3], target = 4，那么算法返回的结果就是：[[1,3],[2,2]]。
     对于修改后的问题，关键难点是现在可能有多个和为 target 的数对儿，还不能重复，比如上述例子中 [1,3] 和 [3,1] 就算重复，只能算一次。
     */

    public static List<List<Integer>> twoSumTarget(int[] nums, int target) {
        // nums 数组必须有序
        Arrays.sort(nums);
        int lo = 0, hi = nums.length - 1;
        List<List<Integer>> res = new ArrayList<>();
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            int left = nums[lo], right = nums[hi];
            if (sum < target) {
                while (lo < hi && nums[lo] == left) {
                    lo++;
                }
            } else if (sum > target) {
                while (lo < hi && nums[hi] == right) {
                    hi--;
                }
            } else {
                res.add(Arrays.asList(left, right));
                while (lo < hi && nums[lo] == left) {
                    lo++;
                }
                while (lo < hi && nums[hi] == right) {
                    hi--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = twoSumTarget(new int[]{1, 3, 1, 2, 2, 3}, 4);
        System.out.println(lists.toString());
    }
}
