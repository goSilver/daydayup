package com.silver.sword4offer.q3_q10;

/**
 * Q03；寻找数组中重复的数字
 *
 * @author Chen ShaoHua
 * @date 2021/2/1
 */
public class Q03 {

    private int findRepeatNumber(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i) {
                if (arr[i] == arr[arr[i]]) return arr[i];
                swap(arr, i, arr[i]);
            }
        }
        return -1;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
