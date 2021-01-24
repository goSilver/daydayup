package com.silver.tree;

/**
 * @author csh
 * @date 2021/1/24
 */
public class BuildMaximumBinaryTree {

    public static void main(String[] args) {

    }

    /**
     * 根据给定数组构造最大二叉树
     *
     * @param arr 数组
     * @return 二叉树根节点
     */
    private TreeNode build(int[] arr) {
        return build(arr, 0, arr.length - 1);
    }

    /**
     * 根据给定数组和下标，构建二叉树
     *
     * @param arr 数组
     * @param lo  起始元素下标
     * @param hi  结束元素下标
     * @return 根节点
     */
    private TreeNode build(int[] arr, int lo, int hi) {
        if (lo < hi) return null;

        // 寻找lo和hi区间最大的数
        int index = -1, max = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (arr[i] > max) {
                index = i;
                max = arr[i];
            }
        }

        // 构造根节点
        TreeNode root = new TreeNode(max);

        // 递归处理左右数组
        build(arr, lo, index - 1);
        build(arr, index + 1, hi);
        return root;
    }
}
