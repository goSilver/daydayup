package com.silver.sword4offer.q51_q60;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 和为s的连续正数序列
     * 思路：
     * 滑动窗口。
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        int left = 1, right = 1;
        int sum = 0;
        List<int[]> res = new ArrayList<>();
        while (left <= target / 2) {
            // 向右扩大窗口
            if (sum < target) {
                sum += right;
                right++;
                // 缩小窗口
            } else if (sum > target) {
                sum -= left;
                left--;
            } else {
                // 记录结果
                int[] arr = new int[right - left];
                for (int i = left; i < right; i++) {
                    arr[left - i] = left;
                }
                res.add(arr);
                // 缩小窗口
                sum -= left;
                left++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
