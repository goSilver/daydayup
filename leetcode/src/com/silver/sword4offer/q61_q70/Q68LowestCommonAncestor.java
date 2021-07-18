package com.silver.sword4offer.q61_q70;

import com.silver.sword4offer.TreeNode;

/**
 * 二叉树的最近公共祖先
 *
 * @author csh
 * @date 2021/3/21
 **/
public class Q68LowestCommonAncestor {
    /**
     * 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor1(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor1(root.left, p, q);
        }
        return root;
    }

    /**
     * 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    /**
     * 判断node节点是否存在以root为根节点的树里
     * （自己写的解法的主要方法，没解出来）
     *
     * @param root 根节点
     * @param node 节点
     * @return 是否存在
     */
    private boolean contains(TreeNode root, TreeNode node) {
        // base case
        if (root.val == node.val) return true;
        boolean left = false;
        boolean right = false;
        // 向左子树寻找
        if (root.left != null) {
            left = contains(root.left, node);
        }
        // 向右子树寻找
        if (root.right != null) {
            right = contains(root.right, node);
        }
        // 是否找到
        return left || right;
    }
}
