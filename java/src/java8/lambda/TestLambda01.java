package java8.lambda;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author csh
 * @date 2020/4/6 12:41
 */
public class TestLambda01 {

    /**
     * 匿名内部类
     */
    private void test01(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    /**
     * Lambda表达式
     */
    private void test02(){
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x,y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }


}
