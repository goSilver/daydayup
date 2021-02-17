package com.silver.sword4offer.q21_q30;

import com.silver.labuladong.temp.ListNode;

/**
 * 合并两个有序链表
 *
 * @author csh
 * @date 2021/2/17
 **/
public class q25 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 初始化
        ListNode head = new ListNode(0);
        ListNode cur = head;

        // 循环合并
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }

        // 合并剩余节点，注意
        cur.next = l1 != null ? l1 : l2;
        return head.next;
    }

}
