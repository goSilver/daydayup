package com.silver.labuladong.important.chapter1;

import com.silver.labuladong.tree.TreeNode;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 二叉树最小深度
 * @author csh
 * @date 2021/5/24
 **/
public class MinDepth {

    private int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight())) return depth;

                if (Objects.nonNull(node.getLeft())) queue.offer(node.getLeft());
                if (Objects.nonNull(node.getRight())) queue.offer(node.getRight());
            }
            depth++;
        }
        return depth;
    }
}
