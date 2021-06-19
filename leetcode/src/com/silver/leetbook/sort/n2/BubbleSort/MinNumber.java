package com.silver.leetbook.sort.n2.BubbleSort;

import java.util.Arrays;

/**
 * 把数组排成最小的数
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 * @author csh
 * @date 2021/6/19
 **/
public class MinNumber {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 30, 34, 5, 9};
        MinNumber main = new MinNumber();
        String s = main.byBubble(nums);
        System.out.print(s);
    }

    /**
     * 冒泡排序实现版
     *
     * @param nums
     * @return
     */
    private String byBubble(int[] nums) {
        // 将整数数组转换成字符串数组
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            arr[i] = String.valueOf(nums[i]);

        /*
         * 排序判断规则 为：
         * 若拼接字符串 x + y > y + x，则 x “大于” y ；
         * 反之，若 x + y < y + x，则 x “小于” y ；
         */
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                String xy = arr[j] + arr[j + 1];
                String yx = arr[j + 1] + arr[j];
                if (Long.parseLong(xy) > Long.parseLong(yx)) {
                    swap(arr, j, j + 1);
                }
            }
        }

        // 将排序后的字符串数组中的元素拼接
        StringBuilder sb = new StringBuilder();
        for (String s : arr)
            sb.append(s);

        return sb.toString();
    }

    private void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 内置函数实现
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for (String s : strs)
            res.append(s);
        return res.toString();
    }

    /**
     * 快排实现
     *
     * @param nums
     * @return
     */
    public String byQuickSort(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        quickSort(strs, 0, strs.length - 1);
        StringBuilder res = new StringBuilder();
        for (String s : strs)
            res.append(s);
        return res.toString();
    }

    void quickSort(String[] strs, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        String tmp = strs[i];
        while (i < j) {
            while ((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) j--;
            while ((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }

}
