package com.silver.labuladong.catalog.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个数组nums和一个正整数k，
 * 有一个大小为k的窗口在nums上从左至右滑动，
 * 请输出每次滑动窗口时窗口中的最大值。
 *
 * @author csh
 * @date 2021/5/4
 **/
public class MaxSlidingWindow {

    private int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k-1) {
                // 先填满窗口的前k-1
                window.push(nums[i]);
            } else {
                // 向右滑动窗口，加入新数字
                window.push(nums[i]);
                // 记录窗口（单调队列）最大值
                res.add(window.max());
                // 移除旧数字
                window.pop(nums[i-k+1]);
            }
        }
        // 转成int[]返回
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}
