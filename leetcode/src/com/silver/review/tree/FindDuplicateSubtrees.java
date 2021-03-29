package com.silver.review.tree;

import com.silver.sword4offer.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 寻找重复子树
 *
 * @author csh
 * @date 2021/3/29
 */
public class FindDuplicateSubtrees {

    // 记录所有序列化的树
    private HashMap<String, Integer> memo = new HashMap<>();
    // 结果集
    private LinkedList<TreeNode> res = new LinkedList<>();


    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    private String traverse(TreeNode root) {
        // 处理空节点
        if (root == null) return "#";

        String left = traverse(root.left);
        String right = traverse(root.right);

        // 利用后序遍历来序列化二叉树
        String subTree = left + "," + right + "," + root.val;

        int freq = memo.getOrDefault(subTree, 0);
        // 保证多次重复的子树只记录一次结果集
        if (freq == 1) res.add(root);

        // 将序列化的结果存入一个map，并记录出现次数
        memo.put(subTree, freq+1);
        return subTree;
    }
}
