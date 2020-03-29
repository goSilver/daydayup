package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Java 8 Stream API 练习
 *
 * Stream 的三个操作步骤：
 * 1、创建Stream
 * 2、中间操作
 * 3、终止操作
 *
 * @author csh
 * @date 2020/3/29 15:25
 */
public class TestStreamAPI01 {

    /**
     * 创建Stream的四种方式
     */
    public void test1() {
        // 1、通过Collection系列集合提供的stream()或parallelStream()方法创建
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        // 2、通过Arrays中的静态方法stream()创建
        String[] arr = new String[10];
        Stream<String> stream2 = Arrays.stream(arr);

        // 3、通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of(arr);

        // 4、创建无限流(迭代)
        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 2);
    }
}
