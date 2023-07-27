package com.mxw.leetcode.Demo;

import java.lang.reflect.Field;
import java.util.*;

public class Demo1 {


    public int  findRightTarget(int[] nums , int target){
        int left =0 , right = nums.length-1;

        while (left<=right){
            int mid=left + (right-left)/2;

            if (target>nums[mid]){
                left=mid+1;
            }
            if (target<nums[mid]){
                right=mid-1;
            }
            if (target==nums[mid]){
                left=mid+1;
            }
        }
        if (left-1<0|| left-1>=nums.length){
            return -1;
        }
        return nums[left-1]==target? left-1 : -1;
    }
}
