package com.silver.leetcode.tree;

import com.silver.sword4offer.TreeNode;

/**
 * 最大二叉树
 *
 * @author csh
 * @date 2021/3/3
 **/
public class Q654ConstructMaximumBinaryTree {
    public static void main(String[] args) {
        Q654ConstructMaximumBinaryTree main = new Q654ConstructMaximumBinaryTree();
        int[] nums = new int[]{3,2,1,6,0,5};
        TreeNode treeNode = main.constructMaximumBinaryTree(nums);

    }
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int begin, int end) {
        if (begin >= end) return null;
        // 寻找最大值
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = begin; i < end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        // 递归调用构造左右子树
        root.left = constructMaximumBinaryTree(nums, begin, index);
        root.right = constructMaximumBinaryTree(nums, index+1, end);

        return root;
    }
}
