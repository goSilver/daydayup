package com.silver.gray.chapter5;

/**
 * 大整数相加
 * @author csh
 * @date 2021/4/24
 */
public class BigNumberSum {
    private String bigNumberSum(String a, String b) {
        // 1.把两个大整数用数组逆序存储，数组长度等于较大整数位数加一
        int maxLength = a.length() > b.length() ? a.length() : b.length();
        int[] arrA = new int[maxLength];
        for (int i = 0; i < a.length(); i++) {
            arrA[i] = a.charAt(a.length() - i - 1) - '0';
        }
        int[] arrB = new int[maxLength];
        for (int i = 0; i < b.length(); i++) {
            arrB[i] = b.charAt(b.length() - i - 1) - '0';
        }
        // 2.构建result数组，数组长度等于较大整数位数加一
        int[] result = new int[maxLength + 1];
        // 3.遍历数组，按位相加
        for (int i = 0; i < result.length; i++) {
            int temp = result[i];
            temp += arrA[i];
            temp += arrB[i];
            result[i] = temp;
            // 3.1 进位操作
            result[i + 1] = temp / 10;
        }

        // 4.把result数组再次逆序并转成String
        StringBuilder sb = new StringBuilder();
        // 是否找到大整数的最高有效位
        boolean findFirst = false;
        for (int i = result.length - 1; i >= 0; i--) {
            if (!findFirst) {
                if (result[i] == 0) {
                    continue;
                }
                findFirst = true;
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }


}
