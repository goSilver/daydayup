package hanshunping.chapter04.doublelinkedlist;

/**
 * @author csh
 * @date 2020/5/1 11:38
 */
public class Main {
    public static void main(String[] args){
        // 构造节点
        DoubleDirectionHeroNode node1 = new DoubleDirectionHeroNode(1, "科比", "mamba");
        DoubleDirectionHeroNode node2 = new DoubleDirectionHeroNode(2, "乔丹", "飞人");
        DoubleDirectionHeroNode node3 = new DoubleDirectionHeroNode(3, "艾弗森", "ANSWER");
        DoubleDirectionHeroNode node4 = new DoubleDirectionHeroNode(4, "韦德", "闪电侠");

        // new一个双向链表
        DoubleDirectionLinkedList doubleList = new DoubleDirectionLinkedList();

        // 新增节点
//        doubleList.simpleAdd(node1);
//        doubleList.simpleAdd(node2);
//        doubleList.simpleAdd(node3);
//        doubleList.simpleAdd(node4);

        doubleList.addByOrder(node1);
        doubleList.addByOrder(node4);
        doubleList.addByOrder(node3);
        doubleList.addByOrder(node2);

        doubleList.remove(node4);
        // 打印
        doubleList.print();

    }
}
