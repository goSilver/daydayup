package com.silver.review;

import java.util.Arrays;

/**
 * 两数之积
 * 无序数组，判断是否存在两个数之积为target
 *
 * @author csh
 * @date 2021/4/5
 **/
public class TwoProduct {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,5,3,8,4};
        int target = 20;
        TwoProduct main = new TwoProduct();
        int[] ints = main.twoSum(arr, target);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    private int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        // 排序
        // 双指针
        int left = 0;
        int right = nums.length-1;
        while (left < right) {
            if (nums[left] * nums[right] > target) {
                right--;
            } else if (nums[left] * nums[right] < target) {
                left++;
            }else {
                // 这里是排序后的index，返回原index需要额外处理
                return new int[]{left, right};
            }
        }
        return new int[]{-1, -1};
    }
}
