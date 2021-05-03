package com.silver.labuladong.catalog.chapter3.lru;

/**
 * @author csh
 * @date 2021/5/3
 **/
public class Node {
    public int key;
    public int val;
    public Node next;
    public Node prev;

    public Node (int k, int v) {
        this.key = k;
        this.val = v;
    }
}
