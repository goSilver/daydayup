package com.silver.sword4offer.q21_q30;

import java.util.Stack;

/**
 * 包含min函数的栈
 *
 * @author csh
 * @date 2021/2/19
 **/
public class q30 {
    Stack<Integer> stackA;
    Stack<Integer> stackB;
    public q30() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    public void push(int x) {
        stackA.add(x);
        if (stackB.isEmpty() || stackB.peek() >= x)
            stackB.add(x);
    }

    public void pop() {
        if (stackA.pop().equals(stackB.peek()))
            stackB.pop();
    }

    public int top() {
        return stackA.peek();
    }

    public int min() {
        return stackB.peek();
    }
}
