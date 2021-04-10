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

    /**
     * 注意这里和有序数组去重的解法有一个重要不同，
     * 我们这里是先给nums[slow]赋值然后再给slow++，
     * 这样可以保证nums[0..slow-1]是不包含值为val的元素的，
     * 最后的结果数组长度就是slow。
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
