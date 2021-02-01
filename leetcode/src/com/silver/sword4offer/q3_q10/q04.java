package com.silver.sword4offer.q3_q10;

/**
 * 二维数组中的查找
 *
 * @author Chen ShaoHua
 * @date 2021/2/1
 */
public class q04 {

    public static void main(String[] args) {
        int[][] arr = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        q04 q04 = new q04();
        boolean res = q04.findNumberIn2DArray(arr, 19);
        System.out.println(res);
    }

    /**
     * 思路：
     * 从二维数组的右上角开始寻找
     * 如果右上角的元素大于target，则代表整列大于target，向左移动；
     * 如果右上角的元素小于target，则代表答案也许存在这一列中；
     * 开始向下寻找，如果存在，则为true；不存在，则为false;
     * <p>
     * 同理，也可以从左下角开始寻找。但是不能从左上角或右下角开始寻找。
     *
     * @param matrix 二维数组
     * @param target 目标元素
     * @return 是否存在
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m, n;
        if (matrix == null || (m = matrix.length) == 0 || matrix[0] == null || (n = matrix[0].length) == 0) return false;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target) --j;
            else ++i;
        }
        return false;
    }
}
