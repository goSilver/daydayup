package com.silver.leetcode.hot100.q11_q20;

import java.util.Stack;

/**
 * 接雨水
 * 四种解法：
 * 1、暴力法
 * 2、动态规划
 * 3、单调栈
 * 4、双指针
 *
 * @author csh
 * @date 2021/6/23
 **/
public class q42_trap {

    /**
     * 动态规划
     * 先构造leftMax、rightMax数组
     * 遍历height数组，当前index积水量等于Math.min(leftMax[i], rightMax[i]) - height[i]
     *
     * @param height
     * @return
     */
    public int trapDynamic(int[] height) {
        int len = height.length;
        if (len == 0) return 0;

        // 初始化leftMax数组
        int[] leftMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // 初始化rightMax数组
        int[] rightMax = new int[len];
        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        // 计算答案
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }

    /**
     * 单调栈
     * 构造单调递减栈stack
     * 当当前元素大于peek时，积水量等于Math.min(height[i], height[left]) - height[top]
     * @param height
     * @return
     */
    public int trapStack(int[] height) {
        // stack存的是下标index！！！
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // top是积水位置
                int top = stack.pop();
                // 边界情况
                if (stack.isEmpty()) break;
                int left = stack.peek();
                int curWidth = i - left - 1;
                int curHeight = Math.min(height[i], height[left]) - height[top];
                res += curWidth * curHeight;
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 双指针
     * 左右指针，left、right，并记录leftMax、rightMax
     * height[left] < height[right]时，积水量等于leftMax - height[left]
     * 否则，积水量等于rightMax - height[right]
     *
     * @param height
     * @return
     */
    public int trapDoublePointer(int[] height) {
        int left =0, right = height.length-1;
        int leftMax = 0, rightMax = 0;
        int res = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
