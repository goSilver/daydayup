package com.silver.review.tree;

import com.silver.sword4offer.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 *
 * @author csh
 * @date 2021/4/11
 */
public class PathSum {

    private LinkedList<List<Integer>> res = new LinkedList<>();
    private LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        recur(root, sum);
        return res;
    }

    private void recur(TreeNode root, int sum) {
        if (root == null ) return;

        path.add(root.val);
        sum -= root.val;
        if (sum == 0 && root.left == null && root.right == null) {
            res.add(new LinkedList<>(path));
        }

        recur(root.left, sum);
        recur(root.right, sum);

        // 回溯
        path.removeLast();
    }
}
