package com.silver.sword4offer.q41_q50;

/**
 * 把数组排成最小的数
 * @author csh
 * @date 2021/6/20
 **/
public class q45_MinNumber {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 30, 34, 5, 9};
        q45_MinNumber main = new q45_MinNumber();
        String s = main.minNumber(nums);
        System.out.print(s);
    }

    public String minNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                String xy = "" + nums[j] + nums[j + 1];
                String yx = "" + nums[j + 1] + nums[j];
                if (Long.parseLong(xy) > Long.parseLong(yx)) {
                    swap(nums, j, j + 1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }
        return sb.toString();
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
