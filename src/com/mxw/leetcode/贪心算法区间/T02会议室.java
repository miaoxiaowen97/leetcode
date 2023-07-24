package com.mxw.leetcode.贪心算法区间;

import java.util.Arrays;

public class T02会议室 {

    /**
     给你输入若干形如 [begin, end] 的区间，代表若干会议的开始时间和结束时间，请你计算至少需要申请多少间会议室。

     比如给你输入 meetings = [[0,30],[5,10],[15,20]]，算法应该返回 2，因为后两个会议和第一个会议时间是冲突的，至少申请两个会议室才能让所有会议顺利进行。
     如果会议之间的时间有重叠，那就得额外申请会议室来开会，想求至少需要多少间会议室，就是让你计算同一时刻最多有多少会议在同时进行。
     换句话说，如果把每个会议的起始时间看做一个线段区间，那么题目就是让你求最多有几个重叠区间，仅此而已

     [0,30]      - - - - - - - - - -
     [5,10]        - - -
     [15,20]                - - -
    每个线段的起点为红点，末端为绿点
     假想有一条带着计数器的线，在时间线上从左至右进行扫描，每遇到红色的点，计数器 count 加一，每遇到绿色的点，计数器 count 减一：
     每个时刻有多少个会议在同时进行，就是计数器 count 的值，count 的最大值，就是需要申请的会议室数量。
     扫描线从左向右前进，遇到红点就对计数器加一，遇到绿点就对计数器减一，计数器 count 的最大值就是答案。

     */

    int minMeetingRooms(int[][] meetings) {
        int n = meetings.length;
        int[] begin = new int[n];
        int[] end = new int[n];
        for(int i = 0; i < n; i++) {
            begin[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        Arrays.sort(begin);
        Arrays.sort(end);
        // 扫描过程中的计数器
        int count = 0;
        // 双指针技巧
        int res = 0, i = 0, j = 0;
        while (i < n && j < n) {

            if (begin[i] < end[j]) {
                // 扫描到一个红点
                count++;
                i++;
            } else {
                // 扫描到一个绿点
                count--;
                j++;
            }
            // 记录扫描过程中的最大值
            res = Math.max(res, count);
        }

        return res;
    }

}
