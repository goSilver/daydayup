package com.silver.leetcode.tree;

import com.silver.sword4offer.TreeNode;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * @author csh
 * @date 2021/3/4
 **/
public class Q105BuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 注意：length - 1
        return buildTree(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preBegin, int preEnd,
                               int[] inorder, int inBegin, int inEnd) {
        // base case
        if (preBegin > preEnd) return null;

        // 前序遍历的首节点就是根节点
        int rootVal = preorder[preBegin];
        // 寻找根节点在中序遍历的位置
        int index = 0;
        for (int i = inBegin; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }
        // 构建根节点
        TreeNode root = new TreeNode(rootVal);

        // 构建左右字节点
        root.left = buildTree(preorder, preBegin + 1, preBegin + index - inBegin,
                inorder, inBegin, index - 1);
        root.right = buildTree(preorder, preBegin + 1 + index - inBegin, preEnd,
                inorder, index + 1, inEnd);
        return root;
    }
}
