package com.silver.sword4offer.q3_q10;

import com.silver.labuladong.temp.ListNode;

import java.util.Stack;

/**
 * @author Chen ShaoHua
 * @date 2021/2/1
 */
public class q06 {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = c;
        c.next = b;
        q06 q = new q06();
        int[] res = q.reversePrint(a);
        for (int re : res) {
            System.out.println(re);
        }
    }

    /**
     * 从尾到头打印链表
     * 利用栈结构来协助实现
     *
     * @param head 链表头节点
     * @return 逆序数组
     */
    public int[] reversePrint(ListNode head) {

        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        int sz = stack.size();
        int[] res = new int[sz];
        for (int i = 0; i < sz; i++) {
            res[i] = stack.pop().val;
        }
        return res;
    }
}
