package com.silver.leetcode.stack;

import java.util.Stack;

/**
 * 双栈实现队列
 *
 * @author csh
 * @date 2021/3/22
 */
public class Q232MyQueue {
    // 输入栈
    private Stack<Integer> a;
    // 输出栈
    private Stack<Integer> b;

    /** Initialize your data structure here. */
    public Q232MyQueue() {
        a = new Stack<>();
        b = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        a.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (b.isEmpty()) {
            while (!a.isEmpty()) {
                b.push(a.pop());
            }
        }
        return b.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(b.isEmpty()) {
            while (!a.isEmpty()) {
                b.push(a.pop());
            }
        }
        return b.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return a.isEmpty() && b.isEmpty();
    }
}
