package com.silver.review.s4o;

/**
 * 二维数组中的查找
 * @author csh
 * @date 2021/6/19
 **/
public class q04 {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}};
        q04 q04 = new q04();
        boolean res = q04.findNumberIn2DArray(arr, 30);
        System.out.println(res);
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0 ) return false;
        int height = matrix.length;
        int width = matrix[0].length;
        int i=0,j=width-1;
        while (i< height && j >0) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}
