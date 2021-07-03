package com.silver.leetcode.hot100.review.first;

import com.silver.labuladong.temp.ListNode;

/**
 * @author csh
 * @date 2021/7/3
 **/
public class q02_addTwoNum {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int sum = a + b + carry;
            int digit = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(digit);
            cur = cur.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }
}
