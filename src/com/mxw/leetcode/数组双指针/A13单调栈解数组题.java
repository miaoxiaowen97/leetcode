package com.mxw.leetcode.数组双指针;

import java.util.Stack;

public class A13单调栈解数组题 {

    /**
     「下一个更大元素 I」：
     给你一个数组，返回一个等长的数组，对应索引存储着下一个更大元素，如果没有更大的元素，就存 -1。

     输入一个数组nums = [2,1,2,4,3]，你返回数组[4,2,4,-1,-1]。
     解释：第一个 2 后面比 2 大的数是 4; 1 后面比 1 大的数是 2；第二个 2 后面比 2 大的数是 4;
     4 后面没有比 4 大的数，填 -1；3 后面没有比 3 大的数，填 -1。
              |
              |  |
     |     |  |  |
     |  |  |  |  |
     2  1  2  4  3
     4  2  4  -1 -1

     for 循环要从后往前扫描元素，因为我们借助的是栈的结构，倒着入栈，其实是正着出栈。
     while 循环是把两个「个子高」元素之间的元素排除，因为他们的存在没有意义，前面挡着个「更高」的元素
     ，所以他们不可能被作为后续进来的元素的 Next Great Number 了。
     */

    public static   int[] nextGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            // 判定个子高矮
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                // 矮个起开，反正也被挡着了。。。
                stack.pop();
            }
            // nums[i] 身后的 next great number
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int [] ar={2,1,2,4,3};
        nextGreaterElement(ar);
    }
}
