package com.silver.sword4offer.q31_q40;

import com.silver.sword4offer.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 * @author csh
 * @date 2021/2/20
 **/
public class q34 {

    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        recur(root, sum);
        return res;
    }

    private void recur(TreeNode root, int target) {
        // base case
        if (root == null) return;
        // 记录路径
        path.add(root.val);
        target -= root.val;
        // 递归出口
        if (target == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(path));
        }
        // 递归
        pathSum(root.left, target);
        pathSum(root.right, target);
        // 回溯
        path.removeLast();
    }
}
