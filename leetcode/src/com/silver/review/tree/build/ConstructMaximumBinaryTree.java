package com.silver.review.tree.build;

import com.silver.labuladong.tree.TreeNode;

/**
 * 最大二叉树
 *
 * @author csh
 * @date 2021/3/28
 */
public class ConstructMaximumBinaryTree {

    TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length <= 0) return null;
        return build(nums, 0, nums.length);
    }

    TreeNode build(int[] nums, int begin, int end) {
        if (begin > end) return null;

        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = begin; i < end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }

        TreeNode root = new TreeNode(max);

        root.setLeft(build(nums, begin, index));
        root.setRight(build(nums, index+1, end));
        return root;
    }
}
