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
        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean helper(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val < min || root.val > max)
            return false;
        return helper(root.left, Integer.MIN_VALUE, root.val) && helper(root.right, root.val, Integer.MAX_VALUE);
    }

    long pre = Long.MIN_VALUE;
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST2(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST2(root.right);
    }

}
