package com.mxw.leetcode.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class B02解开密码锁的最少次数 {

    /**
       题目描述：
       你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
       每个拨轮可以自由旋转：例如把 '9' 变为'0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。不能跨位！
       锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
      #列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
       字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。

       例：
       输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
       输出：6
      解释：
      可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。

      注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的 存在0102死锁
      因为当拨动到 "0102" 时这个锁就会被锁定。
      注意 "0100" -> "0101" -> "0201" -> "0202"  这样的序列是不能解锁的 存在0101死锁
      因为当拨动到 "0101" 时这个锁就会被锁定。
     */

    /**
      思路描述：
      参考生活中，我们的行李箱密码忘记了，你该怎么处理呢？直接砸了它？不，我们应该是去穷举所有的可能。从0000，0001开始。
      回到题目中。我们不管所有的限制条件，不管 deadends 和 target 的限制，穷举所有的可能，你会怎么写出这样的逻辑呢。
      首先我们从0000开始，有多少种选择呢？我们可以顺时针，逆时针进行选择。
      一共有"1000", "9000", "0100", "0900"... 共 8 种密码。再以这 8 种密码作为基础，对每个密码再转一下，穷举出所有可能。
      结合我们之前BFS思路，可以抽象成一副树，每个节点有8个相邻的节点，然后求最短距离（最小高度）
                       0000
       /      |   |     \    \    \     \    \
      1000  9000  0100 0900 0010 0090  0001 0009
       |
      2000 0000 1100 1900 1010 1090 1001 1009
     */

    /**
      无约束穷举代码实现：

      将 s[j] 向上拨动一次
      String plusOne(String s, int j) {
          char[] ch = s.toCharArray();
          if (ch[j] == '9'){
              ch[j] = '0';
          }else{
              ch[j] += 1;
          }
          return new String(ch);
      }

     将 s[i] 向下拨动一次
      String minusOne(String s, int j) {
          char[] ch = s.toCharArray();
          if (ch[j] == '0')
              ch[j] = '9';
          else
              ch[j] -= 1;
          return new String(ch);
      }

      BFS 框架，打印出所有可能的密码
      void BFS(String target) {
           Queue<String> q = new LinkedList<>();
           q.offer("0000");

           while (!q.isEmpty()) {
               从第二列开始就为八个选择
               int sz = q.size();
               将当前队列中的所有节点向周围扩散
                for (int i = 0; i < sz; i++) {
                    String cur = q.poll();
                    判断是否到达终点
                    System.out.println(cur);
                    将一个节点的相邻节点加入队列
                    for (int j = 0; j < 4; j++) {
                         String up = plusOne(cur, j);
                         String down = minusOne(cur, j);
                         q.offer(up);
                         q.offer(down);
                    }
                }
           }
      }

      上述代码的问题：
      1.重复循环：比如说我们从 "0000" 拨到 "1000"，但是等从队列拿出 "1000" 时，还会拨出一个 "0000"，这样的话会产生死循环。
      2.没有终止条件，按照题目要求，我们找到 target 就应该结束并返回拨动的次数。
      3.没有对 deadends 的处理，按道理这些「死亡密码」是不能出现的，也就是说你遇到这些密码的时候需要跳过。
     */
    int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>();
        for (String s : deadends) {
            deads.add(s);
        }
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        // 从起点开始启动广度优先搜索
        int step = 0;
        q.offer("0000");
        visited.add("0000");
        while (!q.isEmpty()) {
            int sz = q.size();
            //将当前队列中的所有节点向周围扩散
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                //判断是否到达终点
                if (deads.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }
                //将一个节点的未遍历相邻节点加入队列
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            /* 在这里增加步数 */
            step++;
        }
        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }

    String plusOne(String s, int j) {
         char[] ch = s.toCharArray();
         if (ch[j] == '9'){
             ch[j] = '0';
         }else{
             ch[j] += 1;
         }
         return new String(ch);
    }

    //将 s[i] 向下拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
     }

}
