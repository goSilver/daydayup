package com.silver.leetcode.array.slowfast;

import java.util.Arrays;

/**
 * 最接近的三数之和
 *
 * @author csh
 * @date 2021/2/22
 **/
public class Q16ThreeSumClosest {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,2,1,4};
        int target = 1;
        Q16ThreeSumClosest main = new Q16ThreeSumClosest();
        int res = main.threeSumClosest(nums, target);
        System.out.println(res);
    }

    /**
     * 思路：
     * 先对数组nums进行排序，然后再利用左右指针
     * 找到最佳答案best
     *
     * @param nums 数组
     * @param target 目标值
     * @return 最接近的三数之和
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int best = 10000000;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                best = Math.abs(sum - target) > Math.abs(best - target) ? best : sum;

                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    return target;
                }
            }
        }

        return best;
    }
}
