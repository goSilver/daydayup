package com.silver.easy.q001;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 *
 * @author csh
 */
public class Solution {
    /**
     * 蛮力法：双重循环遍历数组，如果找到相加之和为target，则返回下标数组
     *
     * @param nums 数组
     * @param target 目标之和
     * @return 下标数组
     */
//    public int[] twoSum(int[] nums, int target) {
//        for (int i = 0; i < nums.length; ++i) {
//            for (int j = i + 1; j < nums.length; ++j) {
//                if (nums[i] + nums[j] == target) {
//                    return new int[]{i, j};
//                }
//            }
//        }
//        return null;
//    }

    /**
     * @param nums   数组
     * @param target 目标之和
     * @return 下标数组
     */
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>(8);
        for (int i = 0; i < len; i++) {
            final Integer value = map.get(nums[i]);
            if (value != null) {
                return new int[]{value, i};
            }
            map.put(target - nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(solution.twoSum(nums, target)));
    }
}
