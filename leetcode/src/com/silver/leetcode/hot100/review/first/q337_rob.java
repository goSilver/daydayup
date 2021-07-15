package com.silver.leetcode.hot100.review.first;

import com.silver.sword4offer.TreeNode;

/**
 * 打家劫舍Ⅲ
 *
 * @author csh
 * @date 2021/7/16
 **/
public class q337_rob {
    public int rob(TreeNode root) {
        int[] status = dfs(root);
        return Math.max(status[0], status[1]);
    }

    private int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] l = dfs(root.left);
        int[] r = dfs(root.right);
        int select = root.val + l[1] + r[1];
        int notSel = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{select, notSel};
    }
}
