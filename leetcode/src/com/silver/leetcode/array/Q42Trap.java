package com.silver.leetcode.array;

/**
 * 接雨水
 *
 * @author csh
 * @date 2021/4/3
 **/
public class Q42Trap {

    /**
     * 接雨水（暴力解法）
     *
     * @param height 数组
     * @return 最多接多少雨水
     */
    public int trapA(int[] height) {
        if (height.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            int lMax = 0, rMax = 0;
            // 注意，<=，需要把index为i的元素也算进来
            for (int j = 0; j <= i; j++)
                lMax = Math.max(height[j], lMax);

            for (int j = i; j < height.length; j++)
                rMax = Math.max(height[j], rMax);

            res += Math.min(lMax, rMax) - height[i];
        }
        return res;
    }

    /**
     * 左右指针，巧妙解法
     *
     * @param height 数组
     * @return 雨水
     */
    public int trapB(int[] height) {
        if (height.length == 0) return 0;
        int res = 0;
        int left = 0, right = height.length - 1;
        int lMax = height[0], rMax = height[right];
        while (left < right) {
            lMax = Math.max(lMax, height[left]);
            rMax = Math.max(rMax, height[right]);

            if (lMax < rMax) {
                res += lMax - height[left];
                left++;
            } else {
                res += rMax - height[right];
                right--;
            }
        }
        return res;
    }
}
