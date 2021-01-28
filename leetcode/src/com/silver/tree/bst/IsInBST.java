package com.silver.tree.bst;

import com.silver.tree.TreeNode;

/**
 * @author Chen ShaoHua
 * @date 2021/1/27
 */
public class IsInBST {


    /**
     * 判断target是否存在二叉搜索树中
     *
     * @param root   根节点
     * @param target 目标数
     * @return 是否存在
     */
    private boolean isInBst(TreeNode root, int target) {

        if (root == null) return false;

        if (root.getVal() == target) {
            return true;
        } else if (root.getVal() < target) {
            return isInBst(root.getRight(), target);
        } else if (root.getVal() > target) {
            return isInBst(root.getLeft(), target);
        }
        return false;
    }
}
