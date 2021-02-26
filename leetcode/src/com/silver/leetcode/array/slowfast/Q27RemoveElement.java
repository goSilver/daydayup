package com.silver.leetcode.array.slowfast;

/**
 * 移除元素
 *
 * @author csh
 * @date 2021/2/26
 **/
public class Q27RemoveElement {

    /**
     * 注意这里和有序数组去重的解法有一个重要不同，
     * 我们这里是先给nums[slow]赋值然后再给slow++，
     * 这样可以保证nums[0..slow-1]是不包含值为val的元素的，
     * 最后的结果数组长度就是slow。
     *
     * @param nums
     * @param val
     * @return
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
