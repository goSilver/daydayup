package com.silver.leetcode.hot100.review.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author csh
 * @date 2021/7/3
 **/
public class q15_threeSum {
    public List<List<Integer>> threeSum(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            // 如果第一位数就大于零，就不用计算后面的了
            if (arr[i] > 0) return res;
            // 处理重复
            if (i > 0 && arr[i] == arr[i - 1]) continue;
            int left = i + 1, right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum < 0) {
                    while (left < right && arr[left] == arr[++left]) ;
                } else if (sum > 0) {
                    while (left < right && arr[right] == arr[--right]) ;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(arr[i], arr[left], arr[right])));
                    while (left < right && arr[left] == arr[++left]) ;
                    while (left < right && arr[right] == arr[--right]) ;
                }
            }
        }
        return res;
    }
}
