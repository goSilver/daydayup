package com.silver.leetcode.hot100.q31_q40;

import com.silver.sword4offer.TreeNode;

/**
 * 验证二叉搜索树
 *
 * @author csh
 * @date 2021/6/26
 **/
public class q98_IsValidBST {
    public boolean isValidBST(TreeNode root) {
        return helper(root.left, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean helper(TreeNode root, int min, int max) {
        if (root == null) return true;
        if (root.val < min || root.val > max)
            return false;
        return helper(root.left, Integer.MIN_VALUE, root.val) && helper(root.right, root.val, Integer.MAX_VALUE);
    }
}
