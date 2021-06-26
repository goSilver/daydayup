package com.silver.leetcode.hot100.q21_q30;

/**
 * 单词搜索
 *
 * @author csh
 * @date 2021/6/26
 **/
public class q79_Exist {
    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] vis = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, vis, word, i, j, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] board, boolean[][] vis, String word, int i, int j, int k) {
        if (board[i][j] != word.charAt(k)) {
            return false;
        }
        if (k == word.length() - 1) {
            return true;
        }
        vis[i][j] = true;
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] direction : directions) {
            int newi = i + direction[0], newj = j + direction[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!vis[newi][newj]) {
                    boolean flag = check(board, vis, word, newi, newj, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        vis[i][j] = false;
        return result;
    }

}
