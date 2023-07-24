package com.mxw.leetcode.贪心算法区间;

import java.util.Arrays;
import java.util.Comparator;

public class T01无重叠区间 {
    /**
     给定一个区间的集合intervals，其中 intervals[i] = [starti, endi]。返回 需要移除区间的最小数量，使剩余区间互不重叠。

     输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
     输出: 1
     解释: 移除 [1,3] 后，剩下的区间没有重叠。

     1、从区间集合 intvs 中选择一个区间 x，这个 x 是在当前所有区间中结束最早的（end 最小）。
     [1,2]     - -
     [2,3]       - -
     [1,3]     - - -   (此时将[1,2]往下延展会有交叉部分，需要去除)
     [3,4]       - -
     2、把所有与 x 区间相交的区间从区间集合 intvs 中删除。

     3、重复步骤 1 和 2，直到 intvs 为空为止。之前选出的那些 x 就是最大不相交子集
     */

    /**
     查找不会相交区间
     * @param intvs
     * @return
     */
    public int intervalSchedule(int[][] intvs) {
        if (intvs.length == 0) {
            return 0;
        }
        // 按 end 升序排序
        Arrays.sort(intvs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是 x
        int x_end = intvs[0][1];

        for (int[] interval : intvs) {
            int start = interval[0];
            if (start >= x_end) {
                // 找到下一个选择的区间了
                count++;
                x_end = interval[1];
            }
        }
        return count;
    }

    int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        return n - intervalSchedule(intervals);
    }
}
