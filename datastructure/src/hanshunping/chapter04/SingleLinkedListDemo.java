package hanshunping.chapter04;

import java.util.Objects;
import java.util.Stack;

/**
 * @author csh
 * @date 2020/4/18 14:23
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 先创建节点
        HeroNode hero1 = new HeroNode(1L, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2L, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3L, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4L, "林冲", "豹子头");

        HeroNode hero2Copy = new HeroNode(4L, "卢俊义copy", "玉麒麟copy");

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

        // 打印链表
        singleLinkedList.list();

//        singleLinkedList.update(hero2Copy);
        singleLinkedList.remove(hero2Copy);
//        singleLinkedList.reverse(singleLinkedList.getHead());

        // 打印链表
        singleLinkedList.list();
//        singleLinkedList.reversePrint();

//        System.out.println("链表的长度：" + singleLinkedList.getLength(singleLinkedList.getHead()));
//        System.out.println("倒数第k个节点：" + singleLinkedList.findTheKthFromBottom(singleLinkedList, 0));
    }
}

