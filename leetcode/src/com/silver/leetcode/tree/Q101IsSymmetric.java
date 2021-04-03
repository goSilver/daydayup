package com.silver.leetcode.tree;

import com.silver.sword4offer.TreeNode;

/**
 * 是否镜像二叉树
 *
 * @author csh
 * @date 2021/4/3
 **/
public class Q101IsSymmetric {

    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }
}
