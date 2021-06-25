package com.silver.leetcode.hot100.q21_q30;

/**
 * 最小路径和
 * @author csh
 * @date 2021/6/25
 **/
public class q64_MinPathSum {
    public int minPathSum(int[][] grid) {
        int width = grid[0].length;
        int height = grid.length;
        for (int i = 1; i < width; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        for (int i = 1; i < height; i++) {
            grid[0][i] += grid[0][i - 1];
        }

        for (int i = 1; i < width; i++) {
            for (int j = 1; j < height; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[width - 1][height - 1];
    }
}
