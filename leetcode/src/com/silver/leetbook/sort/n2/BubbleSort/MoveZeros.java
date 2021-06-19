package com.silver.leetbook.sort.n2.BubbleSort;

import java.util.Arrays;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * @author csh
 * @date 2021/6/19
 **/
public class MoveZeros {

    public static void main(String[] args) {
        MoveZeros main = new MoveZeros();
        int[] arr = new int[]{0,3,2,0,4};
        main.moveZeroesByDoublePointer(arr);
        System.out.print(Arrays.toString(arr));
    }
    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] == 0) {
                    swap(nums, j, nums.length - 1 - i);
                }
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void moveZeroesByDoublePointer(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                if (nums[slow] == 0) {
                    nums[slow] = nums[fast];
                    nums[fast] = 0;
                }
                slow++;
            }
            fast++;
        }
    }

}
