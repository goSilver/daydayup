package com.silver.sword4offer.q3_q10;


import com.silver.labuladong.tree.TreeNode;

/**
 * @author Chen ShaoHua
 * @date 2021/2/1
 */
public class q07 {

    public static void main(String[] args) {
        int[] preOrder=new int[]{3,9,20,15,7};
        int[] inOrder = new int[]{9,3,15,20,7};
        q07 q = new q07();
        TreeNode treeNode = q.buildTree(preOrder, inOrder);

        System.out.println(treeNode.getVal());
    }

    /**
     * 根据二叉树的前序、中序遍历重构二叉树
     *
     * @param preOrder 前序
     * @param inOrder 中序
     * @return 二叉树根节点
     */
    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        return buildTree(preOrder, 0, preOrder.length - 1,
                inOrder, 0, inOrder.length - 1);

    }

    public TreeNode buildTree(int[] preOrder, int preStart, int preEnd,
                              int[] inOrder, int inStart, int inEnd) {
        // base case
        if (preStart > preEnd || inStart > inEnd) return null;

        // 前序遍历的第一元素就是二叉树根节点
        int rootVal = preOrder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // 在中序数组中，找到根节点元素的index
        int index = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootVal) {
                index = i;
                break;
            }
        }

        // 左子树节点个数
        int leftSize = index - inStart;

        // 构造左右子树
        TreeNode left = buildTree(preOrder, preStart + 1, preStart + leftSize,
                inOrder, inStart, index - 1);
        TreeNode right = buildTree(preOrder, leftSize + preStart + 1, preEnd,
                inOrder, index + 1, inEnd);

        root.setLeft(left);
        root.setRight(right);
        return root;
    }
}
