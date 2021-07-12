package java8;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee;
        for (int i = 0; i < 5; i++) {
            employee = new Employee("张三",15L,3000.0);
            employeeList.add(employee);
        }

        System.out.println(employeeList.size());
    }
}
