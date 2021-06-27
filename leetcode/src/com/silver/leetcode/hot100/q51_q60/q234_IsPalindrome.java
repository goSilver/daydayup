package com.silver.leetcode.hot100.q51_q60;

import com.silver.labuladong.temp.ListNode;

/**
 * @author csh
 * @date 2021/6/27
 **/
public class q234_IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        // 寻找链表中点
        ListNode mid = getMid(head);
        // 截断并反转
        ListNode head2 = reverse(mid);
        // 迭代比较节点
        while (head2 != null) {
            if (head.val != head2.val) return false;
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    private ListNode reverse(ListNode mid) {
        // 迭代反转
        ListNode pre = null;
        ListNode cur = mid, nxt;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    private ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 如果fast指针没有指向null，说明链表长度为奇数，slow还要再前进一步
        if (fast != null) slow = slow.next;
        return slow;
    }
}
