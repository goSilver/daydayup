package com.silver.sword4offer.q51_q60;

/**
 * @author csh
 * @date 2021/6/20
 **/
public class q57_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[]{nums[left], nums[right]};
            }
        }
        return new int[]{-1, -1};
    }
}
