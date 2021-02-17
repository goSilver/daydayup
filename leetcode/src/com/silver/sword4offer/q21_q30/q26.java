package com.silver.sword4offer.q21_q30;

import com.silver.labuladong.tree.TreeNode;

/**
 * 树的子结构
 *
 * @author csh
 * @date 2021/2/17
 **/
public class q26 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) &&
                (recur(A, B) || isSubStructure(A.getLeft(), B) || isSubStructure(A.getLeft(), B));
    }

    boolean recur(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.getVal() != B.getVal()) return false;
        return recur(A.getLeft(), B.getLeft()) && recur(A.getRight(), B.getRight());
    }

}
