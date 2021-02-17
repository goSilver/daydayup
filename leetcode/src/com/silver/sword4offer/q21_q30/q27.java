package com.silver.sword4offer.q21_q30;


import com.silver.sword4offer.TreeNode;

/**
 * 二叉树的镜像
 *
 * @author csh
 * @date 2021/2/17
 **/
public class q27 {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        if (root.left != null) {
            mirrorTree(root.left);
        }
        if (root.right != null) {
            mirrorTree(root.right);
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}
