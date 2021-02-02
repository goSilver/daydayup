package com.silver.labuladong.tree;

/**
 * 根据前序&中序、中序&后序遍历数组重建二叉树
 *
 * @author csh
 * @date 2021/1/24
 */
public class BuildByOrder {
    public static void main(String[] args) {
        int[] preOrder=new int[]{3,9,20,15,7};
        int[] inOrder = new int[]{9,3,15,20,7};
        BuildByOrder q = new BuildByOrder();
        TreeNode treeNode = q.buildByPreIn(preOrder, inOrder);

        System.out.println(treeNode.getVal());
    }

    private TreeNode buildByPreIn(int[] order1, int[] order2) {
        return buildByPreIn(order1, 0, order1.length - 1,
                order2, 0, order2.length - 1);
    }

    /**
     * 根据前序&中序顺序重建二叉树
     *
     * @param preOrder 前序数组
     * @param preStart 前序起始元素下标
     * @param preEnd   前序结束元素下标
     * @param inOrder  中序数组
     * @param inStart  中序起始元素下标
     * @param inEnd    中序结束元素下标
     * @return 二叉树根节点
     */
    private TreeNode buildByPreIn(int[] preOrder, int preStart, int preEnd,
                                  int[] inOrder, int inStart, int inEnd) {
        // base case
        if (preStart > preEnd) return null;

        // 前序遍历的首位就是根节点
        int rootVal = preOrder[preStart];
        // 寻找中序遍历的分界点
        int index = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootVal) {
                index = i;
                break;
            }
        }

        // 构造根节点
        TreeNode root = new TreeNode(rootVal);

        // 左子树节点个数
        int leftSize = index - inStart;

        // 构造左右子树
        TreeNode left = buildByPreIn(preOrder, preStart + 1, preStart + leftSize,
                inOrder, inStart, index - 1);
        TreeNode right = buildByPreIn(preOrder, preStart + leftSize + 1, preEnd,
                inOrder, index + 1, inEnd);

        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    /**
     * 根据后序&中序重建二叉树
     *
     * @param postOrder 后序数组
     * @param postStart 后序起始元素下标
     * @param postEnd   后序结束元素下标
     * @param inOrder   前序数组
     * @param inStart   前序起始元素下标
     * @param inEnd     前序结束元素下标
     * @return 二叉树根节点
     */
    private TreeNode buildByPostIn(int[] postOrder, int postStart, int postEnd,
                                   int[] inOrder, int inStart, int inEnd) {
        // base case
        if (inStart > inEnd) return null;
        // 后续遍历的末尾元素就是二叉树的根节点
        int rootVal = postOrder[postEnd];
        // 寻找中序遍历的分界点
        int index = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootVal) {
                index = i;
                break;
            }
        }

        // 构造根节点
        TreeNode root = new TreeNode(rootVal);

        // 左子树的节点个数
        int leftSize = index - inStart;

        // 递归构造左右子树
        TreeNode left = buildByPostIn(postOrder, postStart, postStart + leftSize - 1,
                inOrder, inStart, index - 1);
        TreeNode right = buildByPostIn(postOrder, postStart + leftSize, postEnd - 1,
                inOrder, index + 1, inEnd);
        root.setLeft(left);
        root.setRight(right);

        return root;
    }
}
