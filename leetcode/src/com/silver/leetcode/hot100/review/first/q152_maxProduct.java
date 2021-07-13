package com.silver.leetcode.hot100.review.first;

/**
 * 乘积最大子数组
 *
 * @author csh
 * @date 2021/7/13
 **/
public class q152_maxProduct {
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = max, mn = min;
            max = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            min = Math.min(mx * nums[i], Math.min(nums[i], mn * nums[i]));
            res = Math.max(res, max);
        }
        return res;
    }

    public static void main(String[] args) {
        q152_maxProduct main = new q152_maxProduct();
        int[] arr = new int[]{-2, -3, -2, 4};
        int i = main.maxProduct(arr);
        System.out.println(i);
    }
}
