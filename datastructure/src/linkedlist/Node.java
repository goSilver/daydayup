package linkedlist;

/**
 * 链表结点的实体类
 *
 * @author csh
 */
class Node {
    /**
     * 下一个结点
     */
    Node next = null;

    /**
     * 结点数据
     */
    int data;

    Node(int data) {
        this.data = data;
    }
}