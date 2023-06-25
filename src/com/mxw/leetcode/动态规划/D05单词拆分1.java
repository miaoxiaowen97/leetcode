package com.mxw.leetcode.动态规划;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class D05单词拆分1 {

    /**
     给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
     注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     输入: s = "leetcode", wordDict = ["leet", "code"]
     输出: true
     解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成

     输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     输出: false

     */

    List<String> wordDict;
    // 记录是否找到一个合法的答案
    boolean found = false;
    // 记录回溯算法的路径
    LinkedList<String> track = new LinkedList<>();

    // 主函数
    public boolean wordBreak(String s, List<String> wordDict) {
        this.wordDict = wordDict;
        // 执行回溯算法穷举所有可能的组合
        backtrack(s, 0);
        return found;
    }
    // 回溯算法框架
    void backtrack(String s, int i) {
        // base case
        if (found) {
            // 如果已经找到答案，就不要再递归搜索了
            return;
        }
        if (i == s.length()) {
            // 整个 s 都被匹配完成，找到一个合法答案
            found = true;
            return;
        }
        // 回溯算法框架
        for (String word : wordDict) {
            // 看看哪个单词能够匹配 s[i..] 的前缀
            int len = word.length();
            if (i + len <= s.length() && s.substring(i, i + len).equals(word)) {
                // 找到一个单词匹配 s[i..i+len)
                // 做选择
                track.addLast(word);
                // 进入回溯树的下一层，继续匹配 s[i+len..]
                backtrack(s, i + len);
                // 撤销选择
                track.removeLast();
            }
        }
    }

    // 用哈希集合方便快速判断是否存在
    HashSet<String> wordDictSet;
    // 备忘录，-1 代表未计算，0 代表无法凑出，1 代表可以凑出
    int[] memo;
    // 主函数
    public boolean wordBreakV2(String s, List<String> wordDict) {
        // 转化为哈希集合，快速判断元素是否存在
        this.wordDictSet = new HashSet<>(wordDict);
        // 备忘录初始化为 -1
        this.memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return dp(s, 0);
    }

    // 定义：s[i..] 是否能够被拼出
    boolean dp(String s, int i) {
        // base case
        if (i == s.length()) {
            return true;
        }
        // 防止冗余计算
        if (memo[i] != -1) {
            return memo[i] == 0 ? false : true;
        }
        // 遍历 s[i..] 的所有前缀
        for (int len = 1; i + len <= s.length(); len++) {
            // 看看哪些前缀存在 wordDict 中 ap
            String prefix = s.substring(i, i + len);
            if (wordDictSet.contains(prefix)) {
                // 找到一个单词匹配 s[i..i+len)
                // 只要 s[i+len..] 可以被拼出，s[i..] 就能被拼出
                boolean subProblem = dp(s, i + len);
                if (subProblem == true) {
                    memo[i] = 1;
                    return true;
                }
            }
        }
        // s[i..] 无法被拼出
        memo[i] = 0;
        return false;
    }

}
