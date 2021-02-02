package com.silver.labuladong.tree.bst;

import com.silver.labuladong.tree.TreeNode;

/**
 * 将二叉查找树转换为累加树，leetcode 538
 * 思路：
 * 1、二叉查找树的中序遍历是升序
 * 2、改一下递归顺序，即可获得降序
 *
 * @author Chen ShaoHua
 * @date 2021/1/26
 */
public class GreaterSumTree {

    /**
     * 记录当前节点的累加和
     */
    private int sum;

    /**
     * 转换为累加树
     *
     * @param root 根节点
     * @return 根节点
     */
    private TreeNode convertTree(TreeNode root) {
        traverse(root);
        return root;
    }

    /**
     * 遍历二叉树
     *
     * @param root 根节点
     */
    private void traverse(TreeNode root) {
        // base case
        if (root == null) return;

        // 先递归遍历右子树
        traverse(root.getRight());

        // 计算累加和
        sum += root.getVal();
        root.setVal(sum);

        // 再递归遍历左子树
        traverse(root.getLeft());
    }
}
