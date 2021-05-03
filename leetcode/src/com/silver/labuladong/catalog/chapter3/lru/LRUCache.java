package com.silver.labuladong.catalog.chapter3.lru;

import java.util.HashMap;

/**
 * LRUCache 实现
 *
 * @author csh
 * @date 2021/5/3
 **/
public class LRUCache {
    private HashMap<Integer, Node> map;
    private DoubleList cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    /**
     * 将某个key提升为最近使用的
     *
     * @param key 待提升的key
     */
    private void makeRecently(int key){
        Node x = map.get(key);
        // 先从链表中删除这个节点
        cache.remove(x);
        // 重新插到队尾
        cache.addLast(x);
    }

    /**
     * 添加最近使用的元素
     *
     * @param key 待添加元素的key
     * @param value 待添加元素的value
     */
    private void addRecently(int key, int value){
        Node x = new Node(key,value);
        // 将元素添加到队尾
        cache.addLast(x);
        // 在map中添加映射关系
        map.put(key, x);
    }

    /**
     * 删除某个key
     *
     * @param key 待删除的key
     */
    private void deletedKey(int key) {
        // 从map中获取node
        Node x = map.get(key);
        // 删除node
        cache.remove(x);
        // 删除映射关系
        map.remove(key);
    }

    /**
     * 删除最久未使用的元素
     */
    private void removeLastRecently() {
        // 删除队头节点
        Node deleteNode = cache.removeFirst();
        // 获取删除节点的key
        int deleteKey = deleteNode.key;
        // 删除映射关系
        map.remove(deleteKey);
    }
}
