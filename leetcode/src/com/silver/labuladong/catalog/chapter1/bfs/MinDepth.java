package com.silver.labuladong.catalog.chapter1.bfs;

import com.silver.labuladong.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author csh
 * @date 2021/5/1
 */
public class MinDepth {

    private int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.getLeft() == null && cur.getRight() == null)
                    return depth;
                if (cur.getLeft() != null) queue.offer(cur.getLeft());
                if (cur.getRight() != null) queue.offer(cur.getRight());
            }
            depth++;
        }
        return depth;
    }
}
