package com.silver.leetcode.hot100.q01_q10;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author csh
 * @date 2021/5/30
 **/
public class Q001_TwoSum {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4};
        Q001_TwoSum main = new Q001_TwoSum();
        int[] res = main.twoSumMap(arr, 6);
        for (int re : res) {
            System.out.println(re);
        }
    }

    /**
     * 双重for循环，时间复杂度O(n^2)
     *
     * @param nums   数组
     * @param target 目标数
     * @return 坐标
     */
    private int[] twoSumCycle(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 利用map优化时间复杂度，O(n)，空间复杂度O(n)
     *
     * @param nums   数组
     * @param target 目标数
     * @return 下标
     */
    private int[] twoSumMap(int[] nums, int target) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (memo.containsKey(target - nums[i]))
                return new int[]{memo.get(target - nums[i]), i};
            memo.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    /**
     * 此解法只适用于判断数组中是否存在这样的两个数，不能返回index
     *
     * @param nums   数组
     * @param target 目标数
     * @return 是否存在
     */
    private boolean twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return true;
            }
        }
        return false;
    }
}
