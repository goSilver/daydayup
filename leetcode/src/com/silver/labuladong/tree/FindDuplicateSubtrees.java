package com.silver.labuladong.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 寻找重复子树
 * https://mp.weixin.qq.com/s/LJbpo49qppIeRs-FbgjsSQ
 *
 * @author csh
 * @date 2021/1/24
 */
public class FindDuplicateSubtrees {

    // 记录所有子树以及出现的次数
    private HashMap<String, Integer> memo = new HashMap<>();
    // 记录重复的子树根节点
    private LinkedList<TreeNode> res = new LinkedList<>();

    public static void main(String[] args) {

    }

    /**
     * 主方法
     *
     * @param root
     * @return
     */
    private List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    private String traverse(TreeNode root) {
        if (root == null) return "#";

        String left = traverse(root.getLeft());
        String right = traverse(root.getRight());
        String subTree = left + "," + right + "," + root.getVal();

        int freq = memo.get(subTree);
        if (freq >= 1) {
            res.add(root);
        }
        memo.put(subTree, freq + 1);

        return subTree;
    }
}
