package com.silver.tree;

/**
 * @author csh
 * @date 2021/1/24
 */
public class TreeNode {

    private int val;

    private TreeNode left;

    private TreeNode right;

    private TreeNode next;

    public TreeNode(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getNext() {
        return next;
    }

    public void setNext(TreeNode next) {
        this.next = next;
    }
}
