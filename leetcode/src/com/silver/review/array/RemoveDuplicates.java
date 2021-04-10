package com.silver.review.array;

/**
 * 删除排序数组中的重复项
 *
 * @author csh
 * @date 2021/4/10
 */
public class RemoveDuplicates {

    public int removeDuplicates(int[] arr) {
        if (arr.length == 0) return 0;
        int slow = 0, fast = 0;
        while (fast < arr.length) {
            if (arr[slow] != arr[fast]) {
                slow++;
                arr[slow] = arr[fast];
            }
            fast++;
        }
        return slow+1;
    }
}
