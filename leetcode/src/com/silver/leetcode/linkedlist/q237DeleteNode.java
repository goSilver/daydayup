package com.silver.leetcode.linkedlist;

import com.silver.labuladong.temp.ListNode;

/**
 * 删除链表中的节点
 *
 * @author csh
 * @date 2021/2/24
 **/
public class q237DeleteNode {
    /**
     * 思路：
     * 一想到删除节点，第一时间想到的是迭代，比对，然后删除
     * 但是这道题，没有给head节点，而是只给了要删除的节点
     * 所以，只需要将节点node.next的val值和next指针复制过来即可
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
