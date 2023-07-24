package com.mxw.leetcode.贪心算法区间;

import java.util.Arrays;

public class T03视频拼接 {

    /**
     你将会获得一系列视频片段，这些片段来自于一项持续时长为time秒的体育赛事。这些片段可能有所重叠，也可能长度不一。

     使用数组clips 描述所有的视频片段，其中 clips[i] = [starti, endi] 表示：某个视频片段开始于starti并于endi结束。

     输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
     输出：3
     解释：
     选中 [0,2], [8,10], [1,9] 这三个片段。
     然后，按下面的方案重制比赛片段：
     将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
     现在手上的片段为 [0,2] + [2,8] + [8,10]，而这些覆盖了整场比赛 [0, 10]

     [0,2]     - - -
     [1,5]       - - - - -
     [1,9]       - - - - - - - - -
     [4,6]             - - -
     [5,9]               - - - - -
     [8,10]                    - - -

     首先肯定得取[0,2]，然后取[1,9]最长，最后补充[8,10]
     贪心策略就是：每次都选择能到达的最远的地方
     */
    public int videoStitching(int[][] clips, int T) {
        if (T == 0) return 0;
        // 按起点升序排列，起点相同的降序排列
        Arrays.sort(clips, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        // 记录选择的短视频个数
        int res = 0;

        int curEnd = 0, nextEnd = 0;
        int i = 0, n = clips.length;
        while (i < n && clips[i][0] <= curEnd) {
            // 在第 res 个视频的区间内贪心选择下一个视频
            while (i < n && clips[i][0] <= curEnd) {
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }
            // 找到下一个视频，更新 curEnd
            res++;
            curEnd = nextEnd;
            if (curEnd >= T) {
                // 已经可以拼出区间 [0, T]
                return res;
            }
        }
        // 无法连续拼出区间 [0, T]
        return -1;
    }
}
