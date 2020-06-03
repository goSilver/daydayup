package hanshunping.chapter04.joseph;

import java.util.Objects;

/**
 * @author csh
 * @date 2020/5/1 15:01
 */
public class Main {
    public static void main(String[] args) {
        Kid kid1 = new Kid(1);
        Kid kid2 = new Kid(2);
        Kid kid3 = new Kid(3);
        Kid kid4 = new Kid(4);
        Kid kid5 = new Kid(5);

        OneWayCircleLinkedList circleLinkedList = new OneWayCircleLinkedList();
        circleLinkedList.add(kid1);
        circleLinkedList.add(kid2);
        circleLinkedList.add(kid3);
        circleLinkedList.add(kid4);
        circleLinkedList.add(kid5);

        circleLinkedList.print();
    }

    /**
     * 约瑟夫问题：
     * 设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到 m 的那个人出列，
     * 它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
     *
     * 此处设置n=5，k=1，m为可变参数
     *
     * @param circleLinkedList
     * @param m
     */
    void joseph(OneWayCircleLinkedList circleLinkedList, int m) {
        StringBuilder sb = new StringBuilder();
        Kid current = circleLinkedList.getFirst();
        while (Objects.nonNull(current)) {
            int temp = m;
            while (temp > 0){
                current = current.getNext();
                temp--;
            }
            sb.append(current.getNo() + "->");

        }
    }
}
