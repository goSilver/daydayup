package com.silver.sword4offer.q21_q30;

import com.silver.labuladong.temp.ListNode;

/**
 * 链表中倒数第k个节点
 *
 * @author csh
 * @date 2021/2/17
 **/
public class q22 {

    /**
     * 力扣的题解
     * 思路：双指针，former指针先走k步，然后双指针同时向前走，当former到达末尾时，latter指针刚好就在倒数第k
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode former = head, latter = head;
        for(int i = 0; i < k; i++)
            former = former.next;
        while(former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }

    /**
     * 自己写的
     * 思路：先得到链表的长度length，再从头遍历到length-k处
     *
     * @param head 头节点
     * @param k k
     * @return 倒数第k节点
     */
    public ListNode getKthFromEnd2(ListNode head, int k) {
        int length = 0;
        ListNode second = head;
        while (head != null) {
            length++;
            head = head.next;
        }
        int index = length - k;
        while (index > 0) {
            second = second.next;
            index--;
        }
        return second;
    }
}
