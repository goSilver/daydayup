package com.silver.sword4offer.q31_q40;

import java.util.Arrays;

/**
 * 数组中出现次数超过一半的数字
 * @author csh
 * @date 2021/6/20
 **/
public class q39_MajorityElement {

    /**
     * 排序法
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 摩尔投票法
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

}
