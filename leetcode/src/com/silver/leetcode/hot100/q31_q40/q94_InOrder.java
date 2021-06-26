package com.silver.leetcode.hot100.q31_q40;

import com.silver.sword4offer.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 中序遍历
 * @author csh
 * @date 2021/6/26
 **/
public class q94_InOrder {
    private List<Integer> res = new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        res.add(root.val);
        traverse(root.right);
    }
}
