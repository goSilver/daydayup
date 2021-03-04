package com.silver.leetcode.tree;

import com.silver.sword4offer.TreeNode;

/**
 * 从中序与后序遍历序列构造二叉树
 *
 * @author csh
 * @date 2021/3/4
 **/
public class Q106BuildTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                               int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd) return null;
        int rootVal = postorder[postEnd];
        int index = 0;
        for (int i = inStart; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }

        TreeNode root = new TreeNode(rootVal);
        int leftSize = index - inStart;
        root.left = buildTree(inorder, inStart, index - 1,
                postorder, postStart, postStart + leftSize - 1);
        root.right = buildTree(inorder, index + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1);
        return root;
    }
}
