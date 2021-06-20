package com.silver.sword4offer.q51_q60;

/**
 * @author csh
 * @date 2021/6/20
 **/
public class q53_Search {

    /**
     * 在排序数组中查找数字 I
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }

    private int helper(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        // <=，小于等于
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] <= target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }

    /**
     * 0～n-1中缺失的数字
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] != mid) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
}
