package com.silver.doublepointer.slowfast;

import com.silver.medium.q002.ListNode;

/**
 * 快慢指针的常见算法
 * 1、判定链表中是否含有环
 * 2、已知链表中含有环，返回这个环的起始位置
 * 3、寻找链表的中点
 * 4、寻找链表的倒数第 k 个元素
 *
 * @author csh
 * @date 2021/01/29
 */
public class SlowAndFastPointer {

    /**
     * 判断链表是否有环
     *
     * @param head 头节点
     * @return 是否有环
     */
    private boolean hasCycle(ListNode head) {
        // 初始快慢指针
        ListNode fast, slow;
        fast = slow = head;

        // 遍历链表
        while (fast != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // 如果快慢指针碰撞了，则表示有环
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * 已知链表有环，寻找环的起始节点
     *
     * @param head 头节点
     * @return 环的起始节点
     */
    private ListNode detectCycle(ListNode head) {
        // 初始化快慢指针
        ListNode fast, slow;
        fast = slow = head;

        // 遍历链表，找到快慢指针碰撞的位置
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) break;
        }

        // 慢指针指向头节点
        slow = head;
        // 当快慢指针步伐一致下再次碰撞，即是环的起始节点
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
