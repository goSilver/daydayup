package com.silver.leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈（一个队列实现版）
 *
 * @author csh
 * @date 2021/3/22
 */
public class Q225MyStack2 {
    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public Q225MyStack2() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        // 注意，先获取size，再添加元素
        int size = queue.size();
        queue.offer(x);
        // 利用循环，将队列中的元素反转
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
