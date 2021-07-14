package com.silver.leetcode.hot100.q41_q50;

import java.util.Arrays;

/**
 * 多数元素
 *
 * @author csh
 * @date 2021/7/14
 **/
public class q169_MajorityElement {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
