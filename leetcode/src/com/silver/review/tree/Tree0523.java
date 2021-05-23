package com.silver.review.tree;

import com.silver.leetcode.tree.Node;
import com.silver.sword4offer.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author csh
 * @date 2021/5/23
 **/
public class Tree0523 {
    // 反转二叉树

    /**
     * 反转二叉树
     * 思路：后续遍历处，交换左右字节点
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        return root;
    }

    /**
     * 填充每个节点的下一个节点的右侧指针
     * 思路：利用辅助方法，链接两个字节点以及相邻节点
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
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
     * 思路：后续遍历，将左节点设为右节点，根的左设为空，再遍历此时的右子树找到尾巴，链接右节点
     *
     * @param root
     */
    public void flattern(TreeNode root) {
        if (root == null) return;
        flattern(root.left);
        flattern(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.right = left;
        root.left = null;

        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    /**
     * 构建最大二叉树
     * 思路：
     * 构建辅助方法，找到指定区间的最大的一个数max，new一个最大节点TreeNode(max)，
     * 然后，根据最大的数的左右区间，分别构建这个最大node的左右字节点
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int begin, int end) {
        if (begin > end) return null;

        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = begin; i < end; i++) {
            max = nums[i] > max ? nums[i] : max;
            index = i;
        }
        TreeNode root = new TreeNode(max);
        root.left = build(nums, 0, index);
        root.right = build(nums, index + 1, nums.length);
        return root;
    }

    /**
     * 寻找最近公共祖先
     * 思路：
     * 递归遍历树，如果遇到p，q节点，就向上返回
     * 如果左右两边一边非空，就返回找到的节点，如果都为空则返回空
     * 如果两边都不为空，则返回根节点（公共祖先）
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    private HashMap<String, Integer> memo = new HashMap<>();
    private List<TreeNode> res = new LinkedList<>();

    /**
     * 寻找重复子树
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
        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + "," + right + "," + root.val;
        int freq = memo.getOrDefault(subTree, 0);
        if (freq > 1) res.add(root);
        memo.put(subTree, freq + 1);
        return subTree;
    }

    private int rank = 0;
    private TreeNode kthNode;

    /**
     * 寻找二叉搜索树中第k大节点
     * 思路：
     * 利用二叉搜索树的中序遍历是升序的特性
     *
     * @param root
     * @param k
     */
    public void findKthInBST(TreeNode root, int k) {
        if (root == null) return;
        findKthInBST(root, k);
        rank++;
        if (rank == k) {
            kthNode = root;
            return;
        }
        findKthInBST(root, k);
    }

    /**
     * 把二叉搜索树转换为累加树
     * 思路：调换遍历顺序。
     * 这段代码可以从大到小降序打印 BST 节点的值，
     * 如果维护一个外部累加变量sum，然后把sum赋值给 BST 中的每一个节点，不就将 BST 转化成累加树了吗？
     *
     * @param root
     * @return
     */
    public TreeNode greaterSumTree(TreeNode root) {
        doSum(root);
        return root;
    }

    private int sum;

    private void doSum(TreeNode root) {
        if (root == null) return;
        doSum(root.right);
        sum += root.val;
        root.val = sum;
        doSum(root.left);
    }

    /**
     * 是否镜像二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        // 重点
        return left.val == right.val && check(left.left, right.right) && check(left.right, right.left);
    }

}
