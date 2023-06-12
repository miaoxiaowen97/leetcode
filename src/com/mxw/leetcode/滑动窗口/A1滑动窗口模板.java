package com.mxw.leetcode.滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class A1滑动窗口模板 {

    /**
     *  双指针，窗口，放大窗口，缩小窗口
     *  难点：
     *  如何向窗口中添加新元素
     *  如何缩小窗口
     *  在窗口滑动的哪个阶段更新结果
     *
     *  int left = 0, right = 0;
     *
     *  while (left < right && right<s.size()) {
     *      // 增大窗口
     *      window.add(s[right]);
     *      right++;
     *
     *     while(window need shrinks ?) {
     *         // 缩小窗口
     *         window.remove(s[left]);
     *         left++;
     *     }
     *  }
     */

    /**
     *  java 模板
     *  void slidingWindow(String s){
     *      窗口 存储了哪几个字符
     *      Map<Character, Integer> window = new HashMap<Character, Integer>();
     *      双指针
     *      int left = 0, right = 0;
     *      while (right < s.length()) {
     *          增加窗口
     *          char c=s.charAt(right)
     *          right++;
     *
     *          !! 进行窗口内数据的一系列更新
     *          if(){
     *            window.put(c,window.getOrDefault(c,0)+1);
     *          }
     *
     *          。。。;
     *          ！！debug 输出的位置
     *          System.out.printf("window: [%d, %d)\n", left, right);
     *
     *          收缩窗口
     *          Boolean shrink= method(window need shrinks ?);
     *          while (left < right && shrink) {
     *              d 是将移出窗口的字符
     *              char d = s.charAt(left);
     *              window.put(d, window.get(d) - 1);
     *              缩小窗口
     *              left++;
     *              进行窗口内数据的一系列更新
     *              ...;
     *             !! 两个 ... 处的操作分别是扩大和缩小窗口的更新操作，等会你会发现它们操作是完全对称的。
     *          }
     *      }
     *  }
     *
     *  总结点：
     *  指针 left, right 不会回退（它们的值只增不减），所以字符串/数组中的每个元素都只会进入窗口一次，然后被移出窗口一次
     */

}
