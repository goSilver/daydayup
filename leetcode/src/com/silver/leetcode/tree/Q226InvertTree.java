package com.silver.leetcode.tree;

import com.silver.sword4offer.TreeNode;

/**
 * 翻转二叉树
 *
 * @author csh
 * @date 2021/3/3
 **/
public class Q226InvertTree {
    /**
     * 翻转二叉树
     *
     * @param root 二叉树根节点
     * @return 翻转后的二叉树根节点
     */
    public TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) return null;

        // 递归翻转
        invertTree(root.left);
        invertTree(root.right);

        // 翻转（也可以放在前序遍历位置处）
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}
