package com.silver.leetcode.hot100.review.first;

import java.util.HashMap;

/**
 * @author csh
 * @date 2021/7/3
 **/
public class q01_twoSum {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (memo.containsKey(target - nums[i])) return new int[]{i, memo.get(target - nums[i])};
            memo.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

}
