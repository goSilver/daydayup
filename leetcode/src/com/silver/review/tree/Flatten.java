package com.silver.review.tree;

import com.silver.sword4offer.TreeNode;

/**
 * 二叉树展开为链表
 *
 * @author csh
 * @date 2021/3/28
 */
public class Flatten {
    public void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;

        TreeNode p = root;
        while (p != null) {
            p = p.right;
        }
        p.right = right;
    }
}
