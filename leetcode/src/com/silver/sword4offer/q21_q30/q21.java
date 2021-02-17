package com.silver.sword4offer.q21_q30;

/**
 * 调整数组顺序使奇数位于偶数前面
 *
 * @author csh
 * @date 2021/2/17
 **/
public class q21 {
    /**
     * 力扣上的优雅写法
     *
     * @param nums 原始数组
     * @return 交换奇偶数过后的数组
     */
    public int[] exchange2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (!isEven(nums[left])) {
                left++;
                continue;
            }
            if (isEven(nums[right])) {
                right++;
                continue;
            }
            swap(nums, left, right);
        }
        return nums;
    }

    /**
     * 利用左右指针，双向遍历并交换数字（自己写的笨解法）
     *
     * @param nums 原始数组
     * @return 交换奇偶数过后的数组
     */
    public int[] exchange(int[] nums) {
        int j = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (isEven(nums[i])) {
                if (i >= j) return nums;
                while (i < j) {
                    if (!isEven(nums[j])) {
                        swap(nums, i, j);
                        break;
                    }
                    j--;
                }
            }
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private boolean isEven(int num) {
        return (num % 2) == 0;
    }
}
