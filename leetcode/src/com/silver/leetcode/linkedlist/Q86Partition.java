package com.silver.leetcode.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * 分隔链表
 *
 * @author csh
 * @date 2021/3/18
 **/
public class Q86Partition {

    /**
     * 思路：
     * 定义两个新链表，遍历原链表
     * small链表记录小于x的节点
     * large链表记录大于等于x的节点
     * 遍历完过后，把small的尾指向large的头
     *
     * @param head 原链表头节点
     * @param x    x
     * @return 分割后的链表
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;

        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        // 注意，断开与原链表的链接
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
