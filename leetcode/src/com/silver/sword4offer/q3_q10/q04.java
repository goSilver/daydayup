package com.silver.sword4offer.q3_q10;

/**
 * 二维数组中的查找
 *
 * @author Chen ShaoHua
 * @date 2021/2/1
 */
public class q04 {

    /**
     * 思路：
     * 从二维数组的右上角开始寻找
     * 如果右上角的元素大于target，则代表整列大于target，向左移动；
     * 如果右上角的元素小于target，则代表答案也许存在这一列中；
     * 开始向下寻找，如果存在，则为true；不存在，则为false;
     *
     * 同理，也可以从左下角开始寻找。但是不能从左上角或右下角开始寻找。
     *
     * @param matrix 二维数组
     * @param target 目标元素
     * @return 是否存在
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        // 行
        int row = matrix.length;
        // 列
        int col = matrix[0].length;

        // 记录第一个小于target的列
        int resCol = 0;
        // 从右往左遍历
        for (int i = col-1; i > 0; i--) {
            int cur = matrix[0][i];
            if (cur < target) {
                resCol = i;
            }
        }

        // 从上往下遍历
        for (int j = 0; j < row; j++) {
            int cur = matrix[j][resCol];
            if (cur == target) {
                // 找到目标元素
                return true;
            }
        }
        // 没有找到目标元素
        return false;
    }
}
