package com.mxw.leetcode.动态规划;

import java.util.HashMap;
import java.util.Map;

public class D18鸡蛋掉落 {

    /**
     面前有一栋从 1 到 N 共 N 层的楼，然后给你 K 个鸡蛋（K 至少为 1）。现在确定这栋楼存在楼层 0 <= F <= N，在这层楼将鸡蛋扔下去，
     鸡蛋恰好没摔碎（高于 F 的楼层都会碎，低于 F 的楼层都不会碎，如果鸡蛋没有碎，可以捡回来继续扔）。现在问你，最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层 F 呢？


     如果鸡蛋碎了，那么鸡蛋的个数 K 应该减一，搜索的楼层区间应该从 [1..N] 变为 [1..i-1] 共 i-1 层楼；
     如果鸡蛋没碎，那么鸡蛋的个数 K 不变，搜索的楼层区间应该从 [1..N] 变为 [i+1..N] 共 N-i 层楼。

     */

    private Map<String, Integer> memo = new HashMap<>();

    public int superEggDrop(int K, int N) {
        return dp(K, N);
    }

    private int dp(int K, int N) {
        // base case
        if (K == 1) return N;
        if (N == 0) return 0;
        // 避免重复计算
        String key = K + "," + N;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int res = Integer.MAX_VALUE;
        // 穷举所有可能的选择
        for (int i = 1; i <= N; i++) {
            res = Math.min(res,
                    Math.max(
                            dp(K, N - i),
                            dp(K - 1, i - 1)
                    ) + 1
            );
        }
        // 记入备忘录
        memo.put(key, res);
        return res;
    }

}
