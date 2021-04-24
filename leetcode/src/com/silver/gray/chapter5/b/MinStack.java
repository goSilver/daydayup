package com.silver.gray.chapter5.b;

import java.util.Objects;
import java.util.Stack;

/**
 * 最小栈
 * 思路：
 * push()：main栈直接入栈。当min栈为空或num小于等于min栈栈顶元素时，min栈入栈。
 * pop()：main栈直接pop。如果main栈的栈顶元素等于min栈栈顶元素，min栈也pop。
 * getMin()：直接返回min栈栈顶元素。
 *
 * @author csh
 * @date 2021/4/24
 */
public class MinStack {
    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    private void push(Integer num) {
        mainStack.push(num);
        if (minStack.isEmpty() || num <= minStack.peek()) {
            minStack.push(num);
        }
    }

    private Integer pop() {
        if (Objects.equals(minStack.peek(), mainStack.peek())) {
            minStack.pop();
        }
        return mainStack.pop();
    }

    private Integer getMin() throws Exception{
        if (mainStack.isEmpty()) throw new Exception("stack is empty");
        return minStack.peek();
    }

}
