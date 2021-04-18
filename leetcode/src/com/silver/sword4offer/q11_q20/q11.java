package com.silver.sword4offer.q11_q20;

/**
 * 旋转数组的最小数字
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 *
 * @author csh
 * @date 2021/2/1
 **/
public class q11 {
    public int minArray(int[] numbers) {
        int i = 0, j = numbers.length - 1;

        // 当i=j时，跳出循环
        while (i < j) {
            int mid = (i + j) / 2;
            // 当mid>j，说明结果在mid右边
            if (numbers[mid] > numbers[j]) {
                i = mid + 1;

            // 当mid<j，说明结果在mid左边
            } else if (numbers[mid] < numbers[j]) {
                j = mid;

            // 如果mid=j，无法判断在左还是在右，缩小右侧边界继续
            } else if (numbers[mid] == numbers[j]) {
                j--;
            }
        }
        return numbers[i];
    }
}
