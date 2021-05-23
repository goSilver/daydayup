package com.silver.review.tree;

import com.silver.sword4offer.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的最大、最小深度
 * 重点：求最大时，初始depth为0；求最小时，初始depth为1
 *
 * @author csh
 * @date 2021/4/11
 */
public class MinDepth {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        int depth = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 重点
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
//                if (node.left == null && node.right == null)
//                    return depth;

                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            // 重点
            depth++;
        }
         return depth;
    }

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        List<TreeNode> queue = new LinkedList<TreeNode>() {{ add(root); }}, tmp;
        int res = 0;
        while(!queue.isEmpty()) {
            tmp = new LinkedList<>();
            for(TreeNode node : queue) {
                if(node.left != null) tmp.add(node.left);
                if(node.right != null) tmp.add(node.right);
            }
            queue = tmp;
            res++;
        }
        return res;
    }


}
