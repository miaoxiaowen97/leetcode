package com.mxw.leetcode.数组双指针;

import java.util.HashMap;

public class A04划动窗口模板 {

    /**
     *
     */

    void slidingWindow(String s) {
        HashMap<Character, Integer> window=new HashMap<>();
        int left=0,right=0;

        // 遍历字符串
        while (right<s.length()){
            // 字符串入窗口
            Character c= s.charAt(right);
            // 增大窗口
            right++;

            // 进行窗口内数据的一系列更新。。。。。

            /*** debug 输出的位置 ***/
            // 注意在最终的解法代码中不要 print
            // 因为 IO 操作很耗时，可能导致超时
            System.out.printf("window: [%d, %d)\n", left, right);;
            /********************/

            //判断左侧窗口是否收缩
           Boolean windowNeedsShrink=false;
            while (windowNeedsShrink) {
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 缩小窗口
                left++;
                // 进行窗口内数据的一系列更新  ...
            }
        }
    }
}
