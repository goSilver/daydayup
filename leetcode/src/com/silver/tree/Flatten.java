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

        // 将左子树作为右子树
        root.setRight(root.getLeft());
        root.setLeft(null);

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode temp = root;
        while (temp.getRight() != null) {
            temp = temp.getRight();
        }
        temp.setRight(right);
    }
}
