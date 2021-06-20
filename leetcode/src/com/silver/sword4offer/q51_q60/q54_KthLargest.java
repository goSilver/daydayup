package com.silver.sword4offer.q51_q60;

import com.silver.sword4offer.TreeNode;

/**
 * 二叉搜索树的第k大节点
 * @author csh
 * @date 2021/6/20
 **/
public class q54_KthLargest {
    private int res, k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        if (k == 0) return;
        if (--k == 0) res = root.val;
        dfs(root.left);
    }
}
