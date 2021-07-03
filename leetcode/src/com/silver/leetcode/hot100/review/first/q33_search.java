package com.silver.leetcode.hot100.review.first;

/**
 * @author csh
 * @date 2021/7/4
 **/
public class q33_search {
    public int search(int[] nums, int target) {
        int n = nums.length;
        // base case
        if (n == 0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;

        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            // 找到有序区间，进行判断
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target <= nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
