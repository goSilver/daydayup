package com.silver.leetcode.hot100.q41_q50;

import java.util.Stack;

/**
 * 最小栈
 *
 * @author csh
 * @date 2021/6/27
 **/
public class q155_MinStack {
    private Stack<Integer> in;
    private Stack<Integer> min;
    public q155_MinStack() {
        this.in = new Stack<>();
        this.min = new Stack<>();
    }

    public void push(int val) {
        in.push(val);
        if (min.isEmpty() || val <= min.peek()) min.push(val);
    }

    public void pop() {
        if (in.peek().equals(min.peek())) min.pop();
        in.pop();
    }

    public int top() {
        return in.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
