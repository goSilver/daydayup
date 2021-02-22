package com.silver.leetcode;

import com.silver.labuladong.temp.ListNode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * @author csh
 * @date 2021/2/21
 **/
public class q61 {

    /**
     * 旋转链表
     * 1、先把链表连成环，并得到节点个数num
     * 2、确定新的头节点、尾节点位置
     * 3、切断环状结构
     *
     * @param head 头节点
     * @param k    k
     * @return 旋转后的链表的头节点
     */
    public ListNode rotateRight(ListNode head, int k) {
        // base case
        if (head == null || head.next == null) return head;

        // 记录最初的链表尾
        ListNode oldTail = head;
        // 链表的节点个数
        int num;
        // 将单向链表连成一个环
        for (num = 1; oldTail.next != null; num++) {
            oldTail = oldTail.next;
        }
        oldTail.next = head;

        // 寻找新的头节点、尾节点
        ListNode newTail = head;
        for (int i = 0; i < num - k % num - 1; i++) {
            newTail = newTail.next;
        }
        // 新的头节点
        ListNode newHead = newTail.next;
        // 切断环状链表
        newTail.next = null;
        return newHead;
    }
}
