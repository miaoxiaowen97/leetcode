package com.mxw.leetcode.数组双指针;

import java.util.Stack;

public class A14单调栈解环形数组题 {

    /**
     「下一个更大元素 I」：
     比如输入一个数组[2,1,2,4,3]，你返回数组[4,2,4,-1,4]。拥有了环形属性，最后一个元素 3 绕了一圈后找到了比自己大的元素 4

     一般是通过 % 运算符求模（余数），来获得环形特效：
     int[] arr = {1,2,3,4,5};
     int n = arr.length, index = 0;
     while (true) {
         print(arr[index % n]);
         index++;
     }
              |               |
              |  |            |  |
     |     |  |  |   |     |  |  |
     |  |  |  |  |   |  |  |  |  |
     2  1  2  4  3   2  1  2  4  3
     4  2  4 -1  4

     我们可以不用构造新数组，而是利用循环数组的技巧来模拟数组长度翻倍的效果。
     */

    public static  int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            res[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
        }

        return res;
    }

    public static void main(String[] args) {
        int [] ar={2,1,2,4,3};
        nextGreaterElement(ar);
    }
}
