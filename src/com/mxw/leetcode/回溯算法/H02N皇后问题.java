package com.mxw.leetcode.回溯算法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class H02N皇后问题 {
    /**
     * 给你一个 N×N 的棋盘，让你放置 N 个皇后，使得它们不能互相攻击，请你计算出所有可能的放法。
     *  4x4 的棋盘上放置 4 个皇后，返回以下结果（用 . 代表空棋盘，Q 代表皇后）：返回两种可能
     *  ".Q.."        "..Q."
     *  "...Q"        "Q..."
     *  "Q..."        "...Q"
     *  "..Q."        ".Q.."
     *
     * 皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位
     *
     *  棋盘上的每一行；每个节点可以做出的选择是，在该行的任意一列放置一个皇后
     * 回溯算法的「决策树」
     *                .
     *      1/     2|   \3   \ 4  第一行可供的选择
     *      .      .     .     .
     *    /  \    /  \  /  \  /  \  第二行可供的选择(淘汰不符合的)
     */

    public final static List<List<String>> res = new ArrayList<>();
    public static List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        backtrack(board, 0);
        return res;
    }

    public static void backtrack(char[][] board, int row) {
        if(row == board.length) {
            res.add(Arrays.stream(board).map(String::valueOf).collect(Collectors.toList()));
            return;
        }
        int length = board[row].length;
        for (int i = 0; i < length; i++) {
            if(!isValid(board, row, i)) continue;
            board[row][i] = 'Q';
            backtrack(board, row + 1);
            board[row][i] = '.';
        }
    }

    public static boolean isValid(char[][] board, int row, int col) {
        // 检查列
        for (int i = 0; i < row; i++) {
            if(board[i][col] == 'Q') {
                return false;
            }
        }
        // 检查左上是否冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        // 检查右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(1);
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println("------------------");
        }

        System.out.println(lists.size());
    }


}
