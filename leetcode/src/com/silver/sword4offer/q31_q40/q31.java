package com.silver.sword4offer.q31_q40;

import java.util.Stack;

/**
 * 栈的压入、弹出序列
 *
 * @author csh
 * @date 2021/2/19
 **/
public class q31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek().equals(popped[i])){
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
