package com.silver.review.s4o;

import com.silver.sword4offer.TreeNode;

/**
 * 重构二叉树
 * @author csh
 * @date 2021/6/19
 **/
public class q07_BuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preOrder, int preS, int preE,
                           int[] inOrder, int inS, int inE) {
        if (preS > preE || inS > inE) return null;
        int rootVal = preOrder[preS];
        TreeNode root = new TreeNode(rootVal);

        int index = 0;
        for (int i = inS; i < inE; i++) {
            if (inOrder[i] == rootVal) {
                index = i;
                break;
            }
        }
        int leftSize = index - inS;

        root.left = build(preOrder, preS + 1, preS + leftSize,
                inOrder, inS, index - 1);
        root.right = build(preOrder, preS + leftSize + 1, preE,
                inOrder, index + 1, inE);
        return root;
    }
}
