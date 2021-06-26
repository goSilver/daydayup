package com.silver.leetcode.hot100.q31_q40;

import com.silver.sword4offer.TreeNode;

/**
 * 对称二叉树
 *
 * @author csh
 * @date 2021/6/26
 **/
public class q101_IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && isSymmetric(left.right, right.left) && isSymmetric(left.left, right.right);
    }
}
