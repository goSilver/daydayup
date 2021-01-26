package com.silver.tree.bst;

import com.silver.tree.TreeNode;

/**
 * 判断二叉查找树的合法性
 *
 * @author Chen ShaoHua
 * @date 2021/1/26
 */
public class IsValidBST {

    /**
     * 主方法
     *
     * @param root 根节点
     * @return 是否合法
     */
    private boolean isValid(TreeNode root) {
        return isValid(root, null, null);
    }

    /**
     * 校验二叉树是否合法
     *
     * @param root 根节点
     * @param min 最小节点
     * @param max 最大节点
     * @return 是否合法
     */
    private boolean isValid(TreeNode root, TreeNode min, TreeNode max) {
        // base case
        if (root == null) return true;

        // 如果root的val值不符合min、max的限制，则不合法
        if (min != null && root.getVal() <= min.getVal()) return false;
        if (max != null && root.getVal() >= max.getVal()) return false;

        // 限定左子树的最大值是root，右子树的最大值是root
        return isValid(root.getLeft(), min, root) &&
                isValid(root.getRight(), root, max);
    }
}
