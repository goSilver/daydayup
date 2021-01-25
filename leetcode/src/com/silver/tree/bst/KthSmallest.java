package com.silver.tree.bst;

import com.silver.tree.TreeNode;

/**
 * 寻找二叉查找树中第k小的元素
 *
 * @author csh
 * @date 2021/1/25
 */
public class KthSmallest {

    // 结果
    private int res;

    // 记录当前元素排名
    private int rank;

    /**
     * 主方法
     *
     * @param root 根节点
     * @param k 关键字
     * @return 第k小的元素
     */
    private int kthSmallet(TreeNode root, int k) {
        doFind(root, k);
        return res;
    }

    /**
     * 查找算法
     * 重点：二叉查找树的中序遍历顺序既是一个升序序列
     *
     * @param root 根节点
     * @param k 关键字
     */
    private void doFind(TreeNode root, int k) {
        if (k == 0) return;

        // 前序遍历
        doFind(root.getLeft(),k);

        rank++;
        if (rank == k) {
            // 找到第k小元素
            res = root.getVal();
            return;
        }

        // 后序遍历
        doFind(root.getRight(),k);
    }
}
