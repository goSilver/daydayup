package com.silver.labuladong.catalog.chapter3;

import java.util.LinkedList;

/**
 * 单调队列的实现
 *
 * @author csh
 * @date 2021/5/4
 **/
public class MonotonicQueue {

    private LinkedList<Integer> queue = new LinkedList<>();

    public void push(int n) {
        while (!queue.isEmpty() && queue.getLast() < n) {
            queue.pollLast();
        }
        queue.addLast(n);
    }

    public int max() {
        return queue.getFirst();
    }

    public void pop(int n) {
        if (n == queue.getFirst()) {
            queue.pollFirst();
        }
    }
}
