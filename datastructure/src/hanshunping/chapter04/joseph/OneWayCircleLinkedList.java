package hanshunping.chapter04.joseph;

import java.util.Objects;

/**
 * 单向循环链表
 * 需实现的功能：新增、遍历、
 *
 * @author csh
 * @date 2020/5/1 14:42
 */
public class OneWayCircleLinkedList {

    private Kid first;

    public Kid getFirst(){
        return first;
    }

    /**
     * 新增节点，一直在头结点后面新增，第二次新增的时候将第二个节点的next指向头结点
     * 三种情况：
     * 1、链表为空，直接设置newKid为first
     * 2、只存在first，将newKid的next指向first，构成环状
     * 3、将newKid插入到first之后即可
     *
     * @param newKid 待新增节点
     */
    void add(Kid newKid){
        // 如果头节点为空，先设置头结点
        if (Objects.isNull(first)) {
            first = newKid;
            // 新增第二个节点，构造环状
        } else if (Objects.isNull(first.getNext())) {
            newKid.setNext(first);
            first.setNext(newKid);
        } else {
            // 新增普通节点
            newKid.setNext(first.getNext());
            first.setNext(newKid);
        }
    }

    void remove (Kid rmKid){
        Kid current = first;
        while (Objects.nonNull(current.getNext())) {
            if(Objects.equals(current.getNo(), rmKid.getNo())) {

            }
        }
    }



    /**
     * 遍历打印
     */
    void print(){
        Kid current = first;
        System.out.println(current);
        // 如果当前节点的下一个节点是first，表示已到尾结点
        while (!Objects.equals(current.getNext(), first)) {
            System.out.println(current.getNext());
            current = current.getNext();
        }
    }
}
