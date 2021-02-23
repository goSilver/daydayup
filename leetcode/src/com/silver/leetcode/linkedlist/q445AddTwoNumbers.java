package com.silver.leetcode.linkedlist;

import com.silver.labuladong.temp.ListNode;

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
        ListNode a = new ListNode(5);
        ListNode b = new ListNode(6);
        ListNode x = new ListNode(7);
        ListNode y = new ListNode(8);

        a.next = b;
        x.next = y;
        q445AddTwoNumbers add = new q445AddTwoNumbers();
        // 56+78
        ListNode res = add.addTwoNumbers(a, x);

    }

    /**
     * 两个链表相加
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
