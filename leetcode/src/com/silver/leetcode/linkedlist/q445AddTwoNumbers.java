package com.silver.leetcode.linkedlist;

import com.silver.labuladong.temp.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author csh
 * @date 2021/2/23
 **/
public class q445AddTwoNumbers {
    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(9);
        ListNode x = new ListNode(5);
        ListNode y = new ListNode(6);
        ListNode z = new ListNode(4);
        ListNode z1 = new ListNode(9);
        a.next = b;
        b.next = c;

        x.next = y;
        y.next= z;
        z.next=z1;
        q445AddTwoNumbers add = new q445AddTwoNumbers();
        // 56+78
        ListNode res = add.addTwoNumbers(a, x);

    }

    /**
     * 官方解法，用栈来解，不用改变原链表结构
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<Integer>();
        Deque<Integer> stack2 = new LinkedList<Integer>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode ans = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            int cur = a + b + carry;
            carry = cur / 10;
            cur %= 10;
            ListNode curnode = new ListNode(cur);
            curnode.next = ans;
            ans = curnode;
        }
        return ans;
    }

    /**
     * 两个链表相加，自己写的暴力解法，反转链表后相加
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 相加后的链表头节点
     */
    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 先把两个链表反转
        ListNode node1 = reverse(l1);
        ListNode node2 = reverse(l2);
        // 初始化工具节点
        ListNode res = new ListNode(0);
        ListNode cur = res;
        // 保存进位数
        int carry = 0;
        // 处理 a+b+num 情况
        while (node1 != null && node2 != null) {
            int sum = node1.val + node2.val + carry;
            carry = sum / 10;
            sum %= 10;

            cur.next = new ListNode(sum);
            cur = cur.next;
            node1 = node1.next;
            node2 = node2.next;
        }
        // 处理 a+num 情况
        ListNode temp = node1 != null ? node1 : node2;
        while (temp != null) {
            int sum = temp.val + carry;
            carry = sum / 10;
            sum %= 10;

            cur.next = new ListNode(sum);
            cur = cur.next;
            temp = temp.next;
        }

        // 处理只剩num情况
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }

        // 删除工具头节点，再将链表反转一次就是结果
        cur = res.next;
        res.next = null;
        return reverse(cur);
    }

    /**
     * 反转链表
     *
     * @param head 头节点
     * @return 反转后的头节点
     */
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
}
