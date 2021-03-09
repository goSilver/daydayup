package com.silver.leetcode.tree;

import com.silver.sword4offer.TreeNode;

/**
 * 二叉搜索树中第K小的元素
 *
 * @author csh
 * @date 2021/3/9
 **/
public class Q230KthSmallest {

    // res：结果；rank：当前元素排名
    int res = 0, rank = 0;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    /**
     * 思路：二叉搜索树的中序遍历即是一个升序序列
     *
     * @param root
     * @param k
     */
    private void traverse(TreeNode root, int k) {
        if (root == null) return;

        traverse(root.left, k);

        // 注意，先rank++
        rank++;
        if (rank == k) {
            res = root.val;
            return;
        }

        traverse(root.right, k);
    }
}
