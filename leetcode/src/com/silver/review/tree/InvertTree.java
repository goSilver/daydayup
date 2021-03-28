package com.silver.review.tree;

import com.silver.sword4offer.TreeNode;

/**
 * 翻转二叉树
 *
 * @author csh
 * @date 2021/3/28
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}
