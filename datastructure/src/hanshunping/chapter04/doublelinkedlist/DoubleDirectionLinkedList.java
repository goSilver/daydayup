package hanshunping.chapter04.doublelinkedlist;

import java.util.Objects;

/**
 * @author csh
 * @date 2020/5/1 11:15
 */
public class DoubleDirectionLinkedList {

    // 遍历、增加、删除、修改、打印、获取长度
    // 链表的长度有两种方式获取，1、遍历；2、定义一个类变量记录元素个数

    private DoubleDirectionHeroNode head = new DoubleDirectionHeroNode(0, "", "");

    DoubleDirectionHeroNode getHead() {
        return head;
    }

    /**
     * 正向打印链表
     */
    void print() {
        DoubleDirectionHeroNode current = head;
        while (Objects.nonNull(current.getNextNode())) {
            System.out.println(current.getNextNode().toString());
            current = current.getNextNode();
        }
    }

    /**
     * 简单新增，直接添加到末尾
     *
     * @param newNode 待新增的节点
     */
    void simpleAdd(DoubleDirectionHeroNode newNode) {
        DoubleDirectionHeroNode current = head;
        // 找到末尾节点
        while (Objects.nonNull(current.getNextNode())) {
            current = current.getNextNode();
        }

        // 将新节点添加到末尾节点
        current.setNextNode(newNode);
        newNode.setPreNode(current);
    }

    /**
     * 按序插入
     *
     * @param newNode 待插入node
     */
    void addByOrder(DoubleDirectionHeroNode newNode) {
        // 头结点
        DoubleDirectionHeroNode current = head;
        while (Objects.nonNull(current.getNextNode())) {
            // 如果找到比插入元素的no更大的节点，则将节点插入在这个稍大节点的前面
            if (current.getNextNode().getNo() > newNode.getNo()) {
                // 先定义新插入节点的前后节点
                newNode.setNextNode(current.getNextNode());
                newNode.setPreNode(current);

                // 修改前后的指针
                current.getNextNode().setPreNode(newNode);
                current.setNextNode(newNode);
                return;
            }
            current = current.getNextNode();
        }
        // 如果没有找到比插入元素更大的no，则表示在末尾添加
        current.setNextNode(newNode);
        newNode.setPreNode(current);
    }

    /**
     * 删除节点
     *
     * @param rmNode 待删除节点
     */
    void remove(DoubleDirectionHeroNode rmNode) {
        // 头结点
        DoubleDirectionHeroNode current = head;
        while (Objects.nonNull(current.getNextNode())) {
            if (Objects.equals(current.getNextNode().getNo(), rmNode.getNo())) {
                current.getNextNode().getNextNode().setPreNode(current);
                current.setNextNode(current.getNextNode().getNextNode());
                break;
            }
            current = current.getNextNode();
        }
    }
}
