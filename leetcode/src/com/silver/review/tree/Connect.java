package com.silver.review.tree;

import com.silver.leetcode.tree.Node;

/**
 * 填充每个节点的下一个右侧节点指针
 *
 * @author csh
 * @date 2021/3/28
 */
public class Connect {

    public Node connect(Node root) {
        if (root == null) return null;
        connect(root, root.right);
        return root;
    }

    private void connect(Node left, Node right) {
        if (left == null || right == null) return;

        left.next = right;

        connect(left.left, left.right);
        connect(right.left, right.right);

        connect(left.right, right.left);
    }
}
