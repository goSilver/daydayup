package com.silver.sword4offer.q3_q10;

/**
 * Q03；寻找数组中重复的数字
 *
 * @author Chen ShaoHua
 * @date 2021/2/1
 */
public class Q03 {

    /**
     * 思路：
     * 由题意可知，数组长度为n，且数组内的数字都在0 ~ n-1的范围内；
     * 如果没有重复的数字，则所有数字会等于各自的index值；
     * 故而，如果当前数字与index不相等时，则交换当前位置和当前位置数位置的数，知道相等；
     * 如果当前位置数字与index不相等，且当前位置数字的位置已经存在了一个与index相等的数字，则说明这是一个重复数字。
     *
     * @param arr 数组
     * @return 重复数字
     */
    public int findRepeatNumber(int[] arr) {

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
