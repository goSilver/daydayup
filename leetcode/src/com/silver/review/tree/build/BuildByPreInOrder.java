package com.silver.review.tree.build;

import com.silver.sword4offer.TreeNode;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * @author csh
 * @date 2021/3/28
 */
public class BuildByPreInOrder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length,
                inorder, 0, inorder.length);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) return null;

        int rootVal = preorder[preStart];

        int index = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
            }
        }

        TreeNode root = new TreeNode(rootVal);

        int leftSize = index - inStart;

        root.left = buildTree(
                preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1);

        root.right = buildTree(
                preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);

        return root;
    }
}
