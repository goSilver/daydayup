package com.silver.leetcode.hot100.q31_q40;

import com.silver.sword4offer.TreeNode;

/**
 * @author csh
 * @date 2021/6/26
 **/
public class q114_Flatten {
    public void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;

        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;

    }
}
