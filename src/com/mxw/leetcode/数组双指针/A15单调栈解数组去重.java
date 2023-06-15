package com.mxw.leetcode.数组双指针;

import java.util.Stack;

public class A15单调栈解数组去重 {

    /**
     给你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现-次。需保证返回结果的字典序最小 (要求不能打乱其他字符的相对位置)。

     输入:"bcabc"
     输出:"abc"

     要求一、要去重。
     要求二、去重字符串中的字符顺序不能打乱s中字符出现的相对顺序。
     要求三、在所有符合上一条要求的去重字符串中，字典序最小的作为最终结果。

     */

    /** 用「栈」来实现一下要求一和要求二
     如果输入s = "bcabc"，这个算法会返回"bca"，已经符合要求一和要求二了，但是题目希望要的答案是"abc"对吧
     在向栈stk中插入字符'a'的这一刻，我们的算法需要知道，字符'a'的字典序和之前的两个字符'b'和'c'相比，谁大谁小？
     如果当前字符'a'比之前的字符字典序小，就有可能需要把前面的字符 pop 出栈，让'a'排在前面，对吧？
     */
    String removeDuplicateLettersV1(String s) {
        // 存放去重的结果
        Stack<Character> stk = new Stack<>();
        // 布尔数组初始值为 false，记录栈中是否存在某个字符
        // 输入字符均为 ASCII 字符，所以大小 256 够用了
        boolean[] inStack = new boolean[256];

        for (char c : s.toCharArray()) {
            // 如果字符 c 存在栈中，直接跳过
            if (inStack[c]) {
                continue;
            }
            // 若不存在，则插入栈顶并标记为存在
            stk.push(c);
            inStack[c] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.empty()) {
            sb.append(stk.pop());
        }
        // 栈中元素插入顺序是反的，需要 reverse 一下
        return sb.reverse().toString();
    }

    /**
     假设s = "bcac"，按照刚才的算法逻辑，返回的结果是"ac"，而正确答案应该是"bac"，分析一下这是怎么回事？
     很容易发现，因为s中只有唯一一个'b'，即便字符'a'的字典序比字符'b'要小，字符'b'也不应该被 pop 出去。

     在stk.peek() > c时才会 pop 元素，其实这时候应该分两种情况：
     情况一、如果stk.peek()这个字符之后还会出现，那么可以把它 pop 出去，反正后面还有嘛，后面再 push 到栈里，刚好符合字典序的要求。
     情况二、如果stk.peek()这个字符之后不会出现了，前面也说了栈中不会存在重复的元素，那么就不能把它 pop 出去，否则你就永远失去了这个字符。
     */
    String removeDuplicateLetters(String s) {
        Stack<Character> stk = new Stack<>();
        boolean[] inStack = new boolean[256];

        for (char c : s.toCharArray()) {
            if (inStack[c]) {
                continue;
            }

            // 插入之前，和之前的元素比较一下大小
            // 如果字典序比前面的小，pop 前面的元素
            while (!stk.isEmpty() && stk.peek() > c) {
                // 弹出栈顶元素，并把该元素标记为不在栈中
                inStack[stk.pop()] = false;
            }

            stk.push(c);
            inStack[c] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.empty()) {
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
    }

    /**
        记录每个字符出现了多少次
     */
    String removeDuplicateLettersV3(String s) {

        Stack<Character> stk = new Stack<>();
        // 维护一个计数器记录字符串中字符的数量
        // 因为输入为 ASCII 字符，大小 256 够用了
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        boolean[] inStack = new boolean[256];
        for (char c : s.toCharArray()) {
            // 每遍历过一个字符，都将对应的计数减一
            count[c]--;

            if (inStack[c]) {
                continue;
            }

            while (!stk.isEmpty() && stk.peek() > c) {
                // 若之后不存在栈顶元素了，则停止 pop
                if (count[stk.peek()] == 0) {
                    break;
                }
                // 若之后还有，则可以 pop
                inStack[stk.pop()] = false;
            }
            stk.push(c);
            inStack[c] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.empty()) {
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
    }

}
