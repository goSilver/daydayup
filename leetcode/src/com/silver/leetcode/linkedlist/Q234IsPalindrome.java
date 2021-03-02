package com.silver.leetcode.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * 回文链表
 *
 * @author csh
 * @date 2021/3/2
 **/
public class Q234IsPalindrome {

    /**
     * 思路：
     * 1、找到链表中点
     * 2、反转后半部分
     * 3、对比
     *
     * @param head 头节点
     * @return 是否回文链表
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 如果fast指针没有指向null，说明链表长度为奇数，slow还要再前进一步
        if (fast != null) slow = slow.next;

        ListNode left = head;
        ListNode right = reverse(slow);

        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            right = right.next;
            left = left.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

}
