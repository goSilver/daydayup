package com.silver.labuladong.catalog.chapter3.bst;

import com.silver.labuladong.tree.TreeNode;

/**
 * @author csh
 * @date 2021/5/3
 **/
public class LowestCommonAncestor {

    /**
     * 最近公共祖先
     *
     * @param root 根节点
     * @param p    节点p
     * @param q    节点q
     * @return 最近公共祖先
     */
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.getLeft(), p, q);
        TreeNode right = lowestCommonAncestor(root.getRight(), p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
}
