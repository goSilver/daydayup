package com.silver.leetcode.hot100.q41_q50;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿数量
 *
 * @author csh
 * @date 2021/7/12
 **/
public class q200_NumIslands {
    public int numIslandsDFS(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public int numIslandsBFS(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            i = cur[0]; j = cur[1];
            if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] == '1') {
                grid[i][j] = '0';
                queue.add(new int[]{i + 1, j});
                queue.add(new int[]{i - 1, j});
                queue.add(new int[]{i, j + 1});
                queue.add(new int[]{i, j - 1});
            }

        }
    }
}
