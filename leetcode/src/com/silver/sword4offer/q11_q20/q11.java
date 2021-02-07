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
        int i = 0, j= numbers.length-1;
        while (i<j) {
            int mid = (i+j) / 2;
            if (numbers[mid]>numbers[j]) {
                i=mid+1;
            }else if (numbers[mid]<numbers[j]) {
                j=mid;
            } else {
                j--;
            }
        }
        return numbers[i];
    }
}
