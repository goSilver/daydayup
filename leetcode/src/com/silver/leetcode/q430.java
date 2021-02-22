package com.silver.leetcode;

/**
 * 扁平化多级双向链表
 *
 * @author csh
 * @date 2021/2/21
 **/
public class q430 {
    public Node flatten(Node head) {
        if (head.child != null) {
            head = head.next;
        } else {
            flatten(head.child);

        }
        return null;
    }
}
