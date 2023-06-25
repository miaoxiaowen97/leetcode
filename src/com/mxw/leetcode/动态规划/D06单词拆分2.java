package com.mxw.leetcode.动态规划;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class D06单词拆分2 {

    /**
     * 给定一个字符串 s 和一个字符串字典wordDict，在字符串s中增加空格来构建一个句子，
     * 使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
     * <p>
     * 注意：词典中的同一个单词可能在分段中被重复使用多次。
     * <p>
     * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
     * 输出:["cats and dog","cat sand dog"]
     */

    HashSet<String> wordDict;
    // 备忘录
    List<String>[] memo;

    public List<String> wordBreak(String s, List<String> wordDict) {
        this.wordDict = new HashSet<>(wordDict);
        memo = new List[s.length()];
        return dp(s, 0);
    }

    // 定义：返回用 wordDict 构成 s[i..] 的所有可能
    List<String> dp(String s, int i) {

        List<String> res = new LinkedList<>();
        if (i == s.length()) {
            res.add("");
            return res;
        }

        // 防止冗余计算
        if (memo[i] != null) {
            return memo[i];
        }

        // 遍历 s[i..] 的所有前缀
        for (int len = 1; i + len <= s.length(); len++) {
            // 看看哪些前缀存在 wordDict 中
            String prefix = s.substring(i, i + len);
            if (wordDict.contains(prefix)) {
                // 找到一个单词匹配 s[i..i+len)
                List<String> subProblem = dp(s, i + len);
                // 构成 s[i+len..] 的所有组合加上 prefix
                // 就是构成构成 s[i] 的所有组合
                for (String sub : subProblem) {
                    if (sub.isEmpty()) {
                        // 防止多余的空格
                        res.add(prefix);
                    } else {
                        res.add(prefix + " " + sub);
                    }
                }
            }

        }
        // 存入备忘录
        memo[i] = res;

        return res;

    }
}
