package com.silver.review.array;

/**
 * 旋转数组的最小数字
 *
 * @author csh
 * @date 2021/4/18
 */
public class MinArray {

    public int minArray(int[] arr) {
        int i = 0, j = arr.length;

        while (i < j) {
            int mid = (i + j) / 2;
            if (arr[mid] > arr[j]) {
                i = mid + 1;
            } else if (arr[mid] < arr[j]) {
                j = mid;
            } else if (arr[mid] == arr[j]) {
                j--;
            }
        }
        return arr[i];
    }
}
