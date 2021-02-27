package com.silver.leetcode.array.slowfast;

/**
 * 移动零
 *
 * @author csh
 * @date 2021/2/27
 **/
public class Q283MoveZeroes {
    public void moveZeroes(int[] nums) {
        // 先删除0
        int p = removeElement(nums, 0);
        // 再把后半部分元素全部设为0，即是交换了
        for (; p < nums.length;p++)
            nums[p] =0;
    }

    private int removeElement(int[] nums, int element) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != element) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
