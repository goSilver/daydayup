package com.silver.leetcode.array.slowfast;

/**
 * 删除排序数组中的重复项
 *
 * @author csh
 * @date 2021/2/26
 **/
public class Q26RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        // base case
        if (nums.length == 0) return 0;
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                // 注意。先++，再赋值
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
