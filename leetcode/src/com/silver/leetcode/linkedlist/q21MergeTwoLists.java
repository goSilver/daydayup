package com.silver.leetcode.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * 合并两个有序链表
 *
 * @author csh
 * @date 2021/2/22
 **/
public class q21MergeTwoLists {

    /**
     * 思路：
     * 1、先创建两个节点，一个虚拟head节点，一个cur指向当前节点
     * 2、循环迭代比较
     * 3、处理剩下节点
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 初始化
        ListNode head = new ListNode(0);
        ListNode cur = head;

        // 循环迭代
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            // 移动cur指针
            cur = cur.next;
        }

        // 处理剩下节点
        cur.next = l1 != null ? l1 : l2;
        return head.next;
    }

}
