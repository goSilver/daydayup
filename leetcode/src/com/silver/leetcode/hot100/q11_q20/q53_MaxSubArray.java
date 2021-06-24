package com.silver.leetcode.hot100.q11_q20;

/**
 * 最大子序和
 * @author csh
 * @date 2021/6/24
 **/
public class q53_MaxSubArray {
    public static void main(String[] args) {
        q53_MaxSubArray main = new q53_MaxSubArray();
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int i = main.maxSubArray2(arr);
        System.out.println(i);
    }

    public int maxSubArray(int[] nums) {
        // dp数组
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length - 1; i++) {
            int tmp = Math.max(nums[i], nums[i] + dp[i - 1]);
            dp[i] = tmp > nums[i] ? tmp : nums[i];
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    public int maxSubArray2(int[] nums) {
        int pre = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            max = Math.max(pre, max);
        }
        return max;
    }
}
