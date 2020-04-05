package java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author csh
 * @date 2020/3/30 10:37
 */
public class TestStreamAPI03 {
    public static void main(String[] args){
        // 构造员工列表
        List<Employee> employeeList = Arrays.asList(
                new Employee("张三", 18L, 5000.0),
                new Employee("李四", 23L, 7000.0),
                new Employee("王五", 30L, 10000.0),
                new Employee("赵六", 18L, 9000.0),
                new Employee("田七", 36L, 20000.0)
        );

        Map<Long, List<Employee>> map = employeeList.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(map.toString());

    }
}
