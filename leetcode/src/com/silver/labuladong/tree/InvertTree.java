package com.silver.labuladong.tree;

public class InvertTree {
    public static void main(String[] args) {

    }

    /**
     * 原地反转二叉树
     *
     * @param root 根节点
     * @return 根节点
     */
    private TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) return null;

        // 交换子节点
        TreeNode temp = root.getLeft();
        root.setLeft(root.getRight());
        root.setRight(temp);

        // 递归处理左右子节点
        invertTree(root.getLeft());
        invertTree(root.getRight());

        return root;
    }
}
