package com.silver.sword4offer.q3_q10;

import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * @author csh
 * @date 2021/2/1
 **/
public class q09 {

    /**
     * 新增栈
     */
    private Stack<Integer> a;
    /**
     * 删除栈
     */
    private Stack<Integer> b;

    public q09() {
        a = new Stack<>();
        b = new Stack<>();
    }


    public void appendTail(int value) {
        a.push(value);
    }

    public int deleteHead() {

        if (b.isEmpty()) {
            while (!a.isEmpty()) {
                b.push(a.pop());
            }
        }
        if (b.isEmpty()) {
            return -1;
        } else {
            return b.pop();
        }
    }
}
