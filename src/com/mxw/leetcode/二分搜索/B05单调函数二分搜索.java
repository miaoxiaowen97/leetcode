package com.mxw.leetcode.二分搜索;

public class B05单调函数二分搜索 {

    /**
     什么问题可以运用二分搜索算法技巧？
     1.从题目中抽象出一个自变量 x，一个关于 x 的函数 f(x)，以及一个目标值 target。

     同时，x, f(x), target还要满足以下条件：

     1. f(x) 必须是在 x 上的单调函数（单调增单调减都可以）。
     2. 题目是让你计算满足约束条件 f(x) == target 时的 x 的值。

     // 函数 f 是关于自变量 x 的单调函数
     int f(int x) {
     // ...
     }

     // 主函数，在 f(x) == target 的约束下求 x 的最值
     int solution(int[] nums, int target) {
     if (nums.length == 0) return -1;
     // 问自己：自变量 x 的最小值是多少？
     int left = ...;
     // 问自己：自变量 x 的最大值是多少？
     int right = ... + 1;

     while (left < right) {
         int mid = left + (right - left) / 2;
         if (f(mid) == target) {
                 // 问自己：题目是求左边界还是右边界？
                 // ...
             } else if (f(mid) < target) {
                 // 问自己：怎么让 f(x) 大一点？
                 // ...
             } else if (f(mid) > target) {
                 // 问自己：怎么让 f(x) 小一点？
                 // ...
             }
         }
     return left;
     }

     1. 确定 x, f(x), target 分别是什么，并写出函数 f 的代码。
     2. 找到 x 的取值范围作为二分搜索的搜索区间，初始化 left 和 right 变量。
     3. 根据题目的要求，确定应该使用搜索左侧还是搜索右侧的二分搜索算法，写出解法代码。

     */

    /**
     * 珂珂吃香蕉
     * 珂珂喜欢吃香蕉。这里有N堆香蕉，第堆中有 piles[i] 根香蕉。警卫已经离开了，将在H小时后回来。
     * 珂珂可以决定她吃香蕉的速度 K (单位:根/小时)。每个小时，她将会选择一堆香蕉，从中吃掉 K根如果这堆香蕉少于K根，她将吃掉这堆的所有香蕉，
     * 然后这一小时内不会再吃更多的香蕉
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉
     * 返回她可以在H小时内吃掉所有香蕉的最小速度K(K为整数)

     珂珂每小时最多只能吃一堆香蕉，如果吃不完的话留到下一小时再吃；如果吃完了这一堆还有胃口，也只会等到下一小时才会吃下一堆。

     1. 确定 x, f(x), target 分别是什么，并写出函数 f 的代码。
     题目让求什么，就把什么设为自变量，珂珂吃香蕉的速度就是自变量 x。在 x 上单调的函数关系 f(x) 是什么？
     吃香蕉的速度越快，吃完所有香蕉堆所需的时间就越少，速度和时间就是一个单调函数关系。
     f(x) 函数就可以这样定义：若吃香蕉的速度为 x 根/小时，则需要 f(x) 小时吃完所有香蕉。

     // 定义：速度为 x 时，需要 f(x) 小时吃完所有香蕉
     // f(x) 随着 x 的增加单调递减
     int f(int[] piles, int x) {
         int hours = 0;
         for (int i = 0; i < piles.length; i++) {
             hours += piles[i] / x;
             if (piles[i] % x > 0) {
                 hours++;
             }
         }
         return hours;
     }
     2.target 就很明显了，吃香蕉的时间限制 H 自然就是 target，是对 f(x) 返回值的最大约束。

    3. 找到 x 的取值范围作为二分搜索的搜索区间，初始化 left 和 right 变量
     珂珂吃香蕉的速度最小是多少？多大是多少？
     题目说了 1 <= piles[i] <= 10^9，那么我就可以确定二分搜索的区间边界

     4.确定应该使用搜索左侧还是搜索右侧的二分搜索算法，写出解法代码
     确定了自变量 x 是吃香蕉的速度，f(x) 是单调递减的函数，target 就是吃香蕉的时间限制 H，题目要我们计算最小速度，也就是 x 要尽可能小：
     这就是搜索左侧边界的二分搜索嘛，不过注意 f(x) 是单调递减的，不要闭眼睛套框架
     */
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1;
        int right = 1000000000 + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(piles, mid) == H) {
                // 搜索左侧边界，则需要收缩右侧边界
                right = mid;
            } else if (f(piles, mid) < H) {
                // 需要让 f(x) 的返回值大一些
                right = mid;
            } else if (f(piles, mid) > H) {
                // 需要让 f(x) 的返回值小一些
                left = mid + 1;
            }
        }
        return left;
    }

   public int f(int[] piles, int x) {
        int hours = 0;
        for (int i = 0; i < piles.length; i++) {
            hours += piles[i] / x;
            if (piles[i] % x > 0) {
                hours++;
            }
        }
        return hours;
    }

}
