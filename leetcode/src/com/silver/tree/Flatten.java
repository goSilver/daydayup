package com.silver.tree;

/**
 * @author csh
 * @date 2021/1/24
 */
public class Flatten {
    public static void main(String[] args) {

    }

    private void flatten(TreeNode root) {
        // base case
        if (root == null) return;

        // 递归
        flatten(root.getLeft());
        flatten(root.getRight());

        // 处理已经拉平的左右字树
        TreeNode right = root.getRight();

        root.setRight(root.getLeft());
        root.setLeft(null);

        TreeNode temp = root;
        while (temp.getRight() != null) {
            temp = temp.getRight();
        }
        temp.setRight(right);
    }
}
