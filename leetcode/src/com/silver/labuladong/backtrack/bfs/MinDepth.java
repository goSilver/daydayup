package com.silver.labuladong.backtrack.bfs;

import com.silver.labuladong.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最小深度
 *
 * @author csh
 * @date 2021/2/6
 **/
public class MinDepth {

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node3.setLeft(node9);
        node3.setRight(node20);
        node20.setLeft(node15);
        node20.setRight(node7);

        MinDepth min = new MinDepth();
        int res = min.minDepth(node3);
        System.out.println(res);
    }

    public int minDepth(TreeNode root) {
        // base case
        if (root == null) return 0;
        // 从root节点开始，所以深度初始化为1
        int depth = 1;
        // 核心数据结构，记录每层的节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 遍历层节点
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode cur = queue.poll();

                // 左右子节点都为空的节点就是叶子节点，找到最小深度
                if (cur.getLeft() == null && cur.getRight() == null)
                    return depth;

                // 当当前节点的子节点加入队列
                if (cur.getLeft() != null)
                    queue.offer(cur.getLeft());
                if (cur.getRight() != null)
                    queue.offer(cur.getRight());
            }
            // 当遍历完一层没有找到叶子节点，准备遍历下一层节点时，深度depth加一
            depth++;
        }
        return depth;
    }
}
