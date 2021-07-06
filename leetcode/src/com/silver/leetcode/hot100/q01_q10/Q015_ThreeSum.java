package com.silver.leetcode.hot100.q01_q10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author csh
 * @date 2021/5/30
 **/
public class Q015_ThreeSum {

    /**
     * 是否存在三个数和为0
     * 思路：for循环加双指针，难点是去重
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            // 注意
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    // 注意
                    while (left < right && nums[left] == nums[++left]);
                    while (left < right && nums[right] == nums[--right]);

                } else if (sum > 0) {
                    // 注意
                    while (left < right && nums[right] == nums[--right]);
                } else if (sum < 0) {
                    // 注意
                    while (left < right && nums[left] == nums[++left]);
                }
            }
        }
        return res;
    }


    public List<List<Integer>> threeSum2(int[] arr) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] > 0) break;
            if (i > 0 && arr[i] == arr[i - 1]) continue;
            int left = i + 1, right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == 0) {
                    res.add(new ArrayList<>(Arrays.asList(arr[i], arr[left], arr[right])));

                    while (left < right && arr[left] == arr[++left]);
                    while (left < right && arr[right] == arr[--right]);
                } else if (sum > 0) {
                    while (left < right && arr[right] == arr[--right]);
                } else if (sum < 0) {
                    while (left < right && arr[left] == arr[++left]);
                }
            }
        }
        return res;
    }
}
