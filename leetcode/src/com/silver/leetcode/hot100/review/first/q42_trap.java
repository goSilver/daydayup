package com.silver.leetcode.hot100.review.first;

import java.util.Stack;

/**
 * @author csh
 * @date 2021/7/4
 **/
public class q42_trap {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                int left = stack.peek();
                int w = i - left - 1;
                int h = Math.min(height[i], height[left]) - height[top];
                res += h * w;
            }
            stack.push(i);
        }
        return res;
    }

}
