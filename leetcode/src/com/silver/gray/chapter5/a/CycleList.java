package com.silver.gray.chapter5.a;

import com.silver.labuladong.temp.ListNode;

/**
 * 链表
 * @author csh
 * @date 2021/4/24
 */
public class CycleList {
    /**
     * 判断链表是否有环
     * 思路：
     * 快慢指针，当两指针碰撞时，有环
     *
     * @param head 链表头节点
     * @return 是否有环
     */
    private boolean isCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 寻找环的起点
     * 思路：
     * 快慢指针碰撞时，慢指针指向头节点，再次碰撞时既是环的起始节点
     *
     * @param head 头节点
     * @return 环的起始节点
     */
    private ListNode getCycleHead(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }

        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
