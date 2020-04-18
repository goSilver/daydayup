package hanshunping.chapter04;

import java.util.Objects;

/**
 * @author csh
 * @date 2020/4/18 14:23
 */
public class SingleLinkedListDemo {
    public static void main(String[] args){
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        // 打印链表
        singleLinkedList.list();
    }
}

/**
 * 单向链表实现
 */
class SingleLinkedList{
    /**
     * 链表头结点，不存储具体数据
     */
    private HeroNode head = new HeroNode(0, "", "");

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
        while (!Objects.equals(temp.getNext(), null)) {
            // 将临时变量指向下一个节点
            temp = temp.getNext();
        }
        // 将末尾节点的next指向新节点
        temp.setNext(newNode);
    }

    /**
     * 遍历打印链表节点
     */
    void list(){
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


/**
 * 节点
 */
class HeroNode {
    /**
     * 英雄排名
     */
    private int no;
    /**
     * 英雄姓名
     */
    private String name;
    /**
     * 英雄名号
     */
    private String nickName;
    /**
     * 下一个节点
     */
    private HeroNode next;

    HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}