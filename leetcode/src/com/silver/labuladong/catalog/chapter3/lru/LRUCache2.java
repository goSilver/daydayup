package com.silver.labuladong.catalog.chapter3.lru;

import java.util.LinkedHashMap;

/**
 * LRUCache，LinkedHashMap实现版
 * @author csh
 * @date 2021/5/3
 **/
public class LRUCache2 {
    private int capacity;
    private LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCache2(int capacity) {
        this.capacity = capacity;

    }

    public int get(int key) {
        // 如果不存在这个key，返回-1
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 将key变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        // 如果存在这个key
        if (cache.containsKey(key)) {
            // 更新
            cache.put(key, val);
            // 将key变为最近使用
            makeRecently(key);
            return;
        }
        // 如果容量不够，删除最久未使用
        if (cache.size() == capacity) {
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 添加到队尾巴
        cache.put(key, val);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除key，重新插入队尾
        cache.remove(key);
        cache.put(key, val);
    }
}
