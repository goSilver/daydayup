package com.silver.leetcode.tree;

/**
 * 填充每个节点的下一个右侧节点指针
 *
 * @author csh
 * @date 2021/3/3
 **/
public class Q116Connect {

    public Node connect(Node root) {
        // base case
        if (root == null) return null;
        // 辅助方法
        connectTwoNode(root.left, root.right);
        return root;
    }

    private void connectTwoNode(Node left, Node right) {
        // base case
        if (left == null || right == null) return;

        // 连接相同父节点的两个节点
        left.next = right;

        // 递归连接子节点的字节点
        connectTwoNode(left.left, left.right);
        connectTwoNode(right.left, right.right);

        // 注意。连接跨越父节点的两个子节点
        connectTwoNode(left.right, right.left);
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}