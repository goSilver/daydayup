package com.silver.sword4offer.q51_q60;

import com.silver.sword4offer.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的最大深度
 * @author csh
 * @date 2021/6/20
 **/
public class q55_MaxDepth {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepth2(TreeNode root) {
        if(root == null) return 0;
        List<TreeNode> queue = new LinkedList() {{ add(root); }}, tmp;
        int res = 0;
        while(!queue.isEmpty()) {
            tmp = new LinkedList<>();
            for(TreeNode node : queue) {
                if(node.left != null) tmp.add(node.left);
                if(node.right != null) tmp.add(node.right);
            }
            queue = tmp;
            res++;
        }
        return res;
    }
}
