package com.silver.leetcode.hot100.q31_q40;

import com.silver.sword4offer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最大深度
 *
 * @author csh
 * @date 2021/6/26
 **/
public class q104_MaxDepth {
    public int maxDepth(TreeNode root) {
        int depth = 0;
        if (root == null) return depth;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0 ; i--) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            depth++;
        }
        return depth;
    }

    public int maxDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }
}
