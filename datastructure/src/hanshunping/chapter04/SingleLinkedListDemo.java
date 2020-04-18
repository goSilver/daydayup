package hanshunping.chapter04;

import java.util.Objects;

/**
 * @author csh
 * @date 2020/4/18 14:23
 */
public class SingleLinkedListDemo {
    public static void main(String[] args){
        // 先创建节点
        HeroNode hero1 = new HeroNode(1L, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2L, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3L, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4L, "林冲", "豹子头");

        HeroNode hero2Copy = new HeroNode(2L, "卢俊义copy", "玉麒麟copy");

        // 创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 简单新增
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        // 按排名顺序新增
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        singleLinkedList.update(hero2Copy);
        singleLinkedList.remove(hero2Copy);

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
    private HeroNode head = new HeroNode(0L, "", "");

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
    void addByOrder(HeroNode newNode){
        // 取头节点的拷贝作为辅助遍历变量
        HeroNode temp = head;
        // 如果临时变量节点的下一个节点不为空则继续遍历
        while (Objects.nonNull(temp.getNext())){
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
    void update(HeroNode updateNode){
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
    void remove(HeroNode remNode){
        // 头节点的拷贝，辅助遍历用
        HeroNode temp = head;
        while (Objects.nonNull(temp.getNext())){
            // 删除排名相同的数据
            if (Objects.equals(temp.getNext().getNo(), remNode.getNo())) {
                // 将临时变量的前一个节点的next指向临时变量的next节点即可
                temp.setNext(temp.getNext().getNext());
            }
            // 指针后移
            temp = temp.getNext();
        }
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
    private Long no;
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

    HeroNode(Long no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
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