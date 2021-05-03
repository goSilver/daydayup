package com.silver.labuladong.catalog.chapter3.lru;

/**
 * @author csh
 * @date 2021/5/3
 **/
public class DoubleList {
    /**
     * 头、尾节点
     */
    private Node head;
    private Node tail;
    /**
     * size
     */
    private int size;

    public DoubleList() {
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * 在尾部插入一个节点
     *
     * @param node 待插入节点
     */
    public void addLast(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }

    /**
     * 删除一个节点
     *
     * @param node 待删除节点
     */
    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    /**
     * 删除第一个节点
     *
     * @return 删除的节点
     */
    public Node removeFirst() {
        if (head.next == tail) return null;
        Node first = head.next;
        remove(first);
        return first;
    }

    /**
     * 获取size
     *
     * @return size
     */
    public int size() {
        return size;
    }
}
