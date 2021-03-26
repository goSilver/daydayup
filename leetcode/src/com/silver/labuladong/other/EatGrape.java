package com.silver.labuladong.other;

import java.util.Arrays;

/**
 * 吃葡萄：
 * 有三种葡萄，每种分别有a, b, c颗，
 * 现在有三个人，第一个人只吃第一种和第二种葡萄，第二个人只吃第二种和第三种葡萄，第三个人只吃第一种和第三种葡萄。
 *
 * 现在给你输入a, b, c三个值，请你适当安排，让三个人吃完所有的葡萄，算法返回吃的最多的人最少要吃多少颗葡萄。
 *
 * @author csh
 * @date 2021/3/26
 */
public class EatGrape {

    long solution(long a, long b, long c) {
        long[] nums = new long[]{a, b, c};
        Arrays.sort(nums);
        long sum = a + b + c;

        // 能够构成三角形，可完全平分
        if (nums[0] + nums[1] > nums[2]) {
            return (sum + 2) / 3;
        }
        // 不能构成三角形，平分最长边的情况
        if (2 * (nums[0] + nums[1]) < nums[2]) {
            return (nums[2] + 1) / 2;
        }
        // 不能构成三角形，但依然可以完全平分的情况
        return (sum + 2) / 3;
    }

    /*
     * PS：
     * 向上取整是一个常用的算法技巧。
     * 大部分编程语言中，如果你想计算M除以N，M / N会向下取整，你想向上取整的话，可以改成(M+(N-1)) / N。
     */
}
