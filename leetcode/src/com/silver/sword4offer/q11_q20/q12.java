package com.silver.sword4offer.q11_q20;

import java.util.HashSet;

/**
 * 矩阵中的路径
 *
 * @author Chen ShaoHua
 * @date 2021/2/2
 */
public class q12 {

    public static void main(String[] args) {
        char[][] arr = {{'a', 'b'}, {'c', 'd'}};
        char[][] arr2 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word2 = "ABCCED";
        String word = "abcd";
        q12 q = new q12();
        boolean exist = q.exist(arr2, word2);
        System.out.println(exist);
    }

    /**
     * 自己原创的暴力解法（未通过）
     *
     * @param board 二维数组
     * @param word  字符串
     * @return 是否存在路径
     */
    public boolean exist(char[][] board, String word) {
        HashSet<Integer> rowSet = new HashSet<>();
        char[] chars = word.toCharArray();
        int index = 0;
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            if (rowSet.contains(i)) continue;
            HashSet<Integer> colSet = new HashSet<>();

            for (int j = 0; j < col; j++) {
                if (colSet.contains(j)) continue;
                if (board[i][j] == chars[index]) {
                    colSet.add(j);
                    rowSet.add(i);
                    j = 0;
                    index++;
                    if (index >= chars.length - 1) return true;
                }
            }
        }
        return false;
    }
}
