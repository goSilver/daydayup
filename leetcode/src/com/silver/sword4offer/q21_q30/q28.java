package com.silver.sword4offer.q21_q30;

import com.silver.sword4offer.TreeNode;

/**
 * 对称的二叉树
 *
 * @author csh
 * @date 2021/2/18
 **/
public class q28 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return recur(root.left, root.right);
    }

    private boolean recur(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return recur(left.left, right.right) && recur(left.right, right.left);
    }
}
