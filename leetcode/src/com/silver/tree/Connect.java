package com.silver.tree;

/**
 * @author csh
 * @date 2021/1/24
 */
public class Connect {

    public static void main(String[] args) {

    }

    /**
     * 链接相邻节点
     *
     * @param root 根节点
     * @return 根节点
     */
    private TreeNode connect(TreeNode root) {
        if (root == null) return null;

        connectTwoNodes(root.getLeft(), root.getRight());
        return root;
    }

    /**
     * 给定两个节点，将他们链接
     *
     * @param left  左节点
     * @param right 右节点
     */
    private void connectTwoNodes(TreeNode left, TreeNode right) {

        // base case
        if (left == null || right == null) return;

        // 将传入的两个节点链接
        left.setNext(right);

        // 链接传入节点的子节点
        connectTwoNodes(left.getLeft(), left.getRight());
        connectTwoNodes(right.getLeft(), right.getRight());

        // 链接传入节点的相邻子节点
        connectTwoNodes(left.getRight(), right.getLeft());
    }
}
