package com.silver.labuladong.catalog.chapter3;

import java.util.Stack;

/**
 * 单调栈
 *
 * @author csh
 * @date 2021/5/4
 **/
public class MonotonicStack {
    private int[] nextGreatNumber(int[] nums) {
        // 存放答案的数组
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        // 倒序遍历
        for (int i = nums.length - 1; i > 0; i--) {
            if (!stack.isEmpty() && stack.peek() <= nums[i])
                // 矮的出栈，反正也被挡着了
                stack.pop();
            // 这个元素后面第一个最高的元素
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            // 入栈，接受之后的判定
            stack.push(nums[i]);
        }
        return res;
    }

    private int[] dailyTemperatures(int[] nums) {
        int[] res = new int[nums.length];
        // 存放元素的index，而不是元素
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length; i > 0; i--) {
            if (!stack.isEmpty() && nums[stack.peek()] <= nums[i])
                stack.pop();
            // 得到索引间距
            res[i] = stack.isEmpty() ? 0 : (stack.peek() - i);
            // 存放索引
            stack.push(i);
        }
        return res;
    }

    /**
     * 环形数组求下一个更大数字
     * @param nums
     * @return
     */
    private int[] cycleNextGreatNumber(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        Stack<Integer> stack = new Stack<>();
        // 假装数组长度翻倍了
        for (int i = 2 * length - 1; i > 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % length])
                stack.pop();
            // 利用%求模操作防止索引越界
            res[i % length] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % length]);
        }
        return res;
    }
}
