package com.silver.review.s4o;

import java.util.Stack;

/**
 * 两个栈实现队列
 *
 * @author csh
 * @date 2021/6/19
 **/
public class q09_CQueue {
    Stack<Integer> in;
    Stack<Integer> out;

    public q09_CQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    public void appendTail(int value) {
        in.push(value);
    }

    public int deleteHead() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        if (out.isEmpty()) {
            return -1;
        } else {
            return out.pop();
        }
    }
}
