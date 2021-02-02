package com.silver.labuladong.backtrack.dfs;

import java.util.*;

/**
 * N皇后问题
 *
 * @author csh
 * @date 2021/2/2
 **/
public class SolveNQueens {

    private List<List<String>> res = new ArrayList<>();

    /**
     * 输入棋盘边长 n，返回所有合法的放置
     *
     * @param n 边长
     * @return res
     */
    private List<List<String>> solveNQueens(int n) {
        // 初始化棋盘
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        // 回溯
        backtrack(board, 0);
        return res;
    }

    /**
     * 路径：board 中小于 row 的那些行都已经成功放置了皇后
     * 选择列表：第 row 行的所有列都是放置皇后的选择
     * 结束条件：row 超过 board 的最后一行
     *
     * @param board 棋盘
     * @param row   起始行
     */
    private void backtrack(char[][] board, int row) {
        // 触发结束条件
        if (row == board.length) {
            res.add(array2List(board));
            return;
        }

        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            if (!isValid(board, row, col)) continue;
            // 选择
            board[row][col] = 'Q';
            // 进入下一个决策
            backtrack(board, row + 1);
            // 撤销选择
            board[row][col] = '.';
        }

    }

    /**
     * 二维数组转列表
     *
     * @param board 一个成功的解法，二维数组
     * @return 列表版答案
     */
    private List<String> array2List(char[][] board) {
        List<String> res = new LinkedList<>();
        for (char[] i : board) {
            StringBuilder sb = new StringBuilder();
            for (char j : i) {
                sb.append(j);
            }
            res.add(sb.toString());
        }
        return res;
    }

    /**
     * 检查如果在row、col放置一个皇后是否合法
     *
     * @param board 棋盘
     * @param row   行
     * @param col   列
     * @return 是否合法
     */
    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q')
                return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }
        return true;
    }

}
