package com.silver.leetcode.hot100.q51_q60;

import com.silver.sword4offer.TreeNode;

/**
 * @author csh
 * @date 2021/6/27
 **/
public class q226_InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        invertTree(root.left);
        invertTree(root.right);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

}
