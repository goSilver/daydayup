package hanshunping.chapter04;

import java.util.Objects;
import java.util.Stack;

/**
 * 单向链表实现
 */
class SingleLinkedList {
    /**
     * 链表头结点，不存储具体数据
     */
    private HeroNode head = new HeroNode(0L, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 往单向链表中添加节点
     * 当不考虑添加顺序时：
     * 1、遍历找到最后的节点
     * 2、将最后的节点的next指针指向新的节点
     *
     * @param newNode 待新增的节点
     */
    void add(HeroNode newNode) {
        // 因为链表的头节点不能改变，故使用一个临时变量辅助遍历
        HeroNode temp = head;
        // 如果节点的下一个节点为空则代表此为尾结点
        while (Objects.nonNull(temp.getNext())) {
            // 将临时变量指向下一个节点
            temp = temp.getNext();
        }
        // 将末尾节点的next指向新节点
        temp.setNext(newNode);
    }

    /**
     * 根据英雄排名来按顺序插入
     *
     * @param newNode 待新增的节点
     */
    void addByOrder(HeroNode newNode) {
        // 取头节点的拷贝作为辅助遍历变量
        HeroNode temp = head;
        // 如果临时变量节点的下一个节点不为空则继续遍历
        while (Objects.nonNull(temp.getNext())) {
            // 如果临时变量节点的下一个节点的排名大于待新增节点，则表示应该新增在此临时变量节点的后面
            if (temp.getNext().getNo() > newNode.getNo()) {
                break;
                // 不允许添加相同排名的英雄
            } else if (Objects.equals(temp.getNext().getNo(), newNode.getNo())) {
                System.out.println("已存在排名为：" + newNode.getNo() + " 的好汉，添加失败！");
                return;
            }
            // 临时变量指针往后移一位
            temp = temp.getNext();
        }
        // 新节点的next设置为temp的原next节点
        newNode.setNext(temp.getNext());
        // temp的next设置为新节点
        temp.setNext(newNode);
    }

    /**
     * 节点的更新方法，根据英雄排名更新指定节点，排名不允许更新
     *
     * @param updateNode 带更新的数据
     */
    void update(HeroNode updateNode) {
        // 头节点的拷贝，辅助遍历用
        HeroNode temp = head;
        while (Objects.nonNull(temp.getNext())) {
            // 如果匹配到相同排名的英雄，执行更新操作
            if (Objects.equals(temp.getNo(), updateNode.getNo())) {
                temp.setName(updateNode.getName());
                temp.setNickName(updateNode.getNickName());
                break;
            }
            // 指针向后移一位
            temp = temp.getNext();
        }
    }

    /**
     * 删除操作
     *
     * @param remNode 待删除的节点
     */
    void remove(HeroNode remNode) {
        // 头节点的拷贝，辅助遍历用
        HeroNode temp = head;
        while (Objects.nonNull(temp.getNext())) {
            // 删除排名相同的数据
            if (Objects.equals(temp.getNext().getNo(), remNode.getNo())) {
                // 将临时变量的前一个节点的next指向临时变量的next节点即可
                temp.setNext(temp.getNext().getNext());
                break;
            }
            // 指针后移
            temp = temp.getNext();
        }
    }

    /**
     * 获取链表的长度
     *
     * @param head 链表头结点
     * @return 长度
     */
    int getLength(HeroNode head) {
        int length = 0;
        while (Objects.nonNull(head.getNext())) {
            length++;
            head = head.getNext();
        }
        return length;
    }

    /**
     * 寻找倒数第k个节点
     *
     * @param singleLinkedList 链表
     * @param k                k值
     * @return 倒数第k个节点
     */
    HeroNode findTheKthFromBottom(SingleLinkedList singleLinkedList, int k) {
        int length = getLength(singleLinkedList.head);
        int index = length + 1 - k;
        HeroNode temp = singleLinkedList.head;
        while (Objects.nonNull(singleLinkedList.head.getNext()) && index > 0) {
            temp = temp.getNext();
            index--;
        }
        return temp;
    }

    /**
     * 反转链表
     * 思路：
     * 定义一个新的头结点newHead，遍历链表，依次将遍历的节点取出放到newHead的后面即可
     *
     * @param oldHead 链表的旧头结点
     */
    void reverse(HeroNode oldHead) {
        // 定义一个新的头结点
        HeroNode newHead = new HeroNode(0L, "", "");
        // 定义一个临时节点，记录即将遍历的下一个节点
        HeroNode next;
        // 跳过头节点
        HeroNode current = oldHead.getNext();
        while (Objects.nonNull(current)) {
            // 记录即将遍历的下一个节点
            next = current.getNext();
            // 将newHead的下一个节点设置为当前节点的next
            current.setNext(newHead.getNext());
            // 将newHead的next设置为当前节点
            newHead.setNext(current);
            // 恢复遍历指针
            current = next;
        }
        // 修改链表头结点
        head = newHead;
    }

    /**
     * 逆向打印链表
     * 思路：
     * 1、先反转链表，再打印链表，缺点是会破坏原链表的结构
     * 2、利用栈数据结构先进后出的特性来辅助实现
     * 这里用栈来实现
     */
    void reversePrint() {
        Stack<HeroNode> nodeStack = new Stack<>();
        HeroNode current = head.getNext();
        while (Objects.nonNull(current)) {
            nodeStack.push(current);
            current = current.getNext();
        }
        while (!nodeStack.isEmpty()) {
            System.out.println(nodeStack.pop());
        }
    }

    /**
     * 遍历打印链表节点
     */
    void list() {
        // 链表头结点不允许改变，使用临时变量辅助遍历
        HeroNode temp = head;
        // 如果临时变量的next指针不为空表示链表不为空且未到尾结点
        while (Objects.nonNull(temp.getNext())) {
            System.out.println(temp.getNext());
            // 将next指针后移一位
            temp = temp.getNext();
        }
    }
}
