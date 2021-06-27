package com.silver.leetcode.hot100.q51_q60;

/**
 * 搜索二维矩阵 II
 *
 * @author csh
 * @date 2021/6/27
 **/
public class q240_SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int h = matrix.length, w = matrix[0].length;
        int col = w - 1, row = 0;
        while (col >= 0 && row < h) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }
}
