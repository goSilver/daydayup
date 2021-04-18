package com.silver.review.tree;

import com.silver.leetcode.tree.Node;
import com.silver.sword4offer.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 树
 * @author csh
 * @date 2021/4/11
 */
public class Tree0411 {

    // 反转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    // 填充每个节点的下一个右侧节点指针
    public Node connect(Node root) {
        if (root == null) return null;
        connect(root.left, root.right);
        return root;
    }

    private void connect(Node a, Node b) {
        if (a == null || b == null) return;

        a.next = b;
        connect(a.left, a.right);
        connect(b.left, b.right);

        connect(a.right, b.left);
    }

    /**
     * 二叉树展开为链表
     * 在后续遍历处，利用临时节点保存left、right
     * 将原树left置为null，right置为left
     * 遍历找到新right的尾节点，链接新right和旧right
     *
     * @param root
     */
    public void flattern(TreeNode root) {
        if (root == null) return;

        flattern(root.left);
        flattern(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;

        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    // 构造最大二叉树
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length < 1) return null;
        return build(nums, 0, nums.length);
    }

    private TreeNode build(int[] nums, int begin, int end) {
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
        root.left = build(nums, 0, index);
        root.right = build(nums, index + 1, end);
        return root;
    }

    public TreeNode buildByPreInOrder(int[] preOrder, int[] inOrder) {
        return build(preOrder, 0, preOrder.length - 1,
                inOrder, 0, inOrder.length - 1);
    }

    private TreeNode build(int[] preOrder, int preBegin, int preEnd,
                           int[] inOrder, int inBegin, int inEnd) {
        if (preBegin > preEnd) return null;
        int rootVal = preOrder[preBegin];
        int index = 0;
        for (int i = inBegin; i < inEnd; i++) {
            if (inOrder[i] == rootVal) {
                index = i;
                break;
            }
        }

        TreeNode root = new TreeNode(rootVal);
        int leftSize = index - inBegin;
        root.left = build(preOrder, preBegin + 1, preBegin + leftSize,
                inOrder, inBegin, index - 1);
        root.right = build(preOrder, preBegin + leftSize + 1, preEnd,
                inOrder, index + 1, inEnd);
        return root;
    }

    // 寻找最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    private HashMap<String, Integer> memo = new HashMap<>();
    private LinkedList<TreeNode> res = new LinkedList<>();
    // 寻找重复子树
    private List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    private String traverse(TreeNode root) {
        if (root == null) return "#";

        String left = traverse(root.left);
        String right = traverse(root.right);
        String subTree = left + "," + right + "," + root.val;

        int freq = memo.get(subTree);
        if (freq >= 1) {
            res.add(root);
        }
        memo.put(subTree, freq+1);
        return subTree;
    }

    // 二叉搜索树中第K小的元素
    private int rank;
    private TreeNode kthRes;
    public void findKthInBST(TreeNode root, int k) {
        if (root == null) return;
        findKthInBST(root.left,k);
        rank++;
        if (rank == k) {
            kthRes = root;
            return;
        }
        findKthInBST(root.right,k);
    }

    // 把二叉搜索树转换为累加树
    public TreeNode greaterSumTree(TreeNode root) {
        doSum(root);
        return root;
    }
    private int sum;
    public void doSum(TreeNode root) {
        if (root == null) return;
        doSum(root.right);
        sum += root.val;
        root.val = sum;
        doSum(root.left);
    }

    // 是否镜像二叉树
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }
    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val && check(p.left, q.right) && check(q.left, p.right);
    }

}
