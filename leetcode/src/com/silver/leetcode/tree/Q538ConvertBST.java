package com.silver.leetcode.tree;

import com.silver.sword4offer.TreeNode;

/**
 * 把二叉搜索树转换为累加树
 * 思路：常规二叉树的思路行不通了。利用中序遍历特性，反向递归二叉树即可获得结果。
 *
 * @author csh
 * @date 2021/3/9
 **/
public class Q538ConvertBST {
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.right);

        sum += root.val;
        root.val = sum;

        traverse(root.left);
    }
}
