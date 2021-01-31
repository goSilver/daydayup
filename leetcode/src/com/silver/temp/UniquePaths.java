package com.silver.temp;

/**
 * @author csh
 * @date 2021/01/31
 **/
public class UniquePaths {

    /**
     * 在一个二维矩阵中，机器人在坐标m,n处
     * 求：机器人有多少种路径可以到达左上角
     *
     * @param m 坐标m
     * @param n 坐标n
     * @return 路径种数
     */
    private int uniquePaths(int m, int n) {
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i - 1][j] + arr[j][i - 1];
                }
            }
        }
        return arr[m - 1][n - 1];
    }
}
