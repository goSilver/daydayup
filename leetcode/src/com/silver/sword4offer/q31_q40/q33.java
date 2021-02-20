package com.silver.sword4offer.q31_q40;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果
 *
 * @author csh
 * @date 2021/2/20
 **/
public class q33 {
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length-1);
    }

    private boolean recur(int[] postorder, int i, int j) {
        if (i >= j) return true;
        int p = i;
        while (postorder[p] < postorder[j]) p++;
        int m = p;
        while (postorder[p] > postorder[j]) p++;
        return p == j && recur(postorder, i, m-1) && recur(postorder, m, j-1);
    }
}
