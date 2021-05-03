package com.silver.labuladong.catalog.chapter3.bst;

import com.silver.labuladong.tree.TreeNode;

/**
 * 二叉树的操作
 * @author csh
 * @date 2021/5/3
 **/
public class BSTOperation {

    private void plusOne(TreeNode root) {
        if (root == null) return;
        root.setVal(root.getVal() + 1);
        plusOne(root.getLeft());
        plusOne(root.getRight());
    }

    private boolean isSameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.getVal() != root2.getVal()) return false;

        return isSameTree(root1.getLeft(), root2.getLeft()) &&
                isSameTree(root1.getRight(), root2.getRight());
    }

    private boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;
        if (min != null && root.getVal() <= min.getVal()) return false;
        if (max != null && root.getVal() >= max.getVal()) return false;
        return isValidBST(root, min, root) && isValidBST(root, root, max);
    }

    private boolean isInBST(TreeNode root, int target) {
        if (root == null) return false;
        if (root.getVal() == target) {
            return true;
        } else if (root.getVal() < target) {
            return isInBST(root.getRight(), target);
        } else if (root.getVal() > target) {
            return isInBST(root, target);
        }
        return false;
    }

    private TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.getVal() == val) {
            return root;
        } else if (root.getVal() < val) {
            root.setRight(insertIntoBST(root, val));
        } else if (root.getVal() > val) {
            root.setLeft(insertIntoBST(root, val));
        }
        return root;
    }

    private TreeNode deleteNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.getVal() == val) {
            if (root.getLeft() == null) return root.getRight();
            if (root.getRight() == null) return root.getLeft();
            TreeNode minNode = getMinNode(root.getRight());
            root.setRight(deleteNode(root.getRight(), minNode.getVal()));
        } else if (root.getVal() > val) {
            root.setLeft(deleteNode(root.getLeft(), val));
        } else if (root.getVal() < val) {
            root.setRight(deleteNode(root.getRight(), val));
        }
        return root;
    }

    private TreeNode getMinNode(TreeNode node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }

    private int countNodes(TreeNode root) {
        TreeNode left = root;
        TreeNode right = root;
        // 记录左右子树高度
        int hl = 0, hr = 0;
        while (left != null) {
            left = left.getLeft();
            hl++;
        }
        while (right != null) {
            right = right.getRight();
            hr++;
        }
        // 如果左右子树高度相同，则说明是一颗满二叉树
        if (hl == hr) {
            return (int) Math.pow(2, hl) - 1;
        }
        // 如果左右高度不同，则按普通二叉树来算
        return 1 + countNodes(root.getLeft()) + countNodes(root.getRight());
    }
}
