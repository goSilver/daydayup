package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java 8 Stream API 练习
 * Stream 的三个操作步骤：
 * 1、创建Stream
 * 2、中间操作
 * 3、终止操作
 *
 * @author csh
 * @date 2020/3/29 15:50
 */
public class TestStreamAPI02 {

    /**
     * 中间操作
     * 筛选与切片：
     * filter -> 接收Lambda，根据规则过滤元素
     * limit  -> 截断流，限制流的数据条数
     * skip(n)-> 跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流。与limit(n)互补。
     * distinct-> 去重，根据流所生成的元素的hashCode()和equals()来判断、去重
     *
     * @param args
     */
    public static void main(String[] args) {
        // 构造员工列表
        List<Employee> employeeList = Arrays.asList(
                new Employee("张三", 18L, 5000.0),
                new Employee("李四", 23L, 7000.0),
                new Employee("王五", 30L, 10000.0),
                new Employee("赵六", 26L, 9000.0),
                new Employee("田七", 36L, 20000.0),
                new Employee("田七", 36L, 20000.0)
        );

        // 1、测试filter方法，找出年龄大于等于30的员工
        List<Employee> employeeList1 = testFilter(employeeList);
        // 输出：
        // Employee{name='王五', age='30', salary='10000.0'}
        // Employee{name='田七', age='36', salary='20000.0'}
        employeeList1.forEach(System.out::println);

        // 2、测试limit()方法，找出薪水大于8000的两名员工
        List<Employee> employeeList2 = testLimit(employeeList);
        // 输出：
        // Employee{name='王五', age='30', salary='10000.0'}
        // Employee{name='赵六', age='26', salary='9000.0'}
        employeeList2.forEach(System.out::println);

        // 3、测试skip()方法，跳过前3个元素
        List<Employee> employeeList3 = testSkip(employeeList);
        // 输出：
        // Employee{name='赵六', age='26', salary='9000.0'}
        // Employee{name='田七', age='36', salary='20000.0'}
        employeeList3.forEach(System.out::println);

        // 3、测试distinct()方法，跳过前3个元素
        List<Employee> employeeList4 = testDistinct(employeeList);
        employeeList4.forEach(System.out::println);
    }

    /**
     * 测试distinct方法(Employee对象需重写hashCode和equals方法)
     *
     * @param employeeList 员工列表
     * @return 执行中间操作后的数据
     */
    private static List<Employee> testDistinct(List<Employee> employeeList) {
        return employeeList.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 测试skip方法
     *
     * @param employeeList 员工列表
     * @return 执行中间操作后的数据
     */
    private static List<Employee> testSkip(List<Employee> employeeList) {
        return employeeList.stream()
                .skip(3)
                .collect(Collectors.toList());
    }

    /**
     * filter方法测试，找出年龄大于30的员工
     *
     * @param employeeList 员工列表
     * @return 经过filter()操作后的员工列表
     */
    private static List<Employee> testFilter(List<Employee> employeeList) {
        return employeeList.stream()
                .filter(o -> o.getAge() >= 30)
                .collect(Collectors.toList());
    }

    /**
     * limit方法测试，找出薪水大于8000的两名员工
     *
     * @param employeeList 员工列表
     * @return 经过中间操作后的员工列表
     */
    private static List<Employee> testLimit(List<Employee> employeeList) {
        return employeeList.stream()
                .filter(o -> o.getSalary() >= 8000)
                .limit(2)
                .collect(Collectors.toList());
    }
}
