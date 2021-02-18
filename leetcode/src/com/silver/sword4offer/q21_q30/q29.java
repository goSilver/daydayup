package com.silver.sword4offer.q21_q30;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 顺时针打印矩阵
 *
 * @author csh
 * @date 2021/2/18
 **/
public class q29 {
    public int[] spiralOrder(int[][] matrix) {
        int i = 0, j = 0, m = matrix[0].length, n = matrix.length;
        LinkedList<Integer> res = new LinkedList<>();
        while (i < m) {
            res.add(matrix[i][j]);
            i++;
        }
        j++;
        while (j < n) {
            res.add(matrix[i][j]);
            j++;
        }
        i--;
        while (i>0) {
            res.add(matrix[i][j]);
            i--;
        }
        j--;
        while (j>0) {
            res.add(matrix[i][j]);
            j--;
        }
        return null;
    }
}
