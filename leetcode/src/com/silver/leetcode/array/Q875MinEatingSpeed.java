package com.silver.leetcode.array;

/**
 * 爱吃香蕉的珂珂
 *
 * @author csh
 * @date 2021/2/25
 **/
public class Q875MinEatingSpeed {
    /**
     * 思路：
     * 暴力解法是，speed从1到max，遇到满足条件的就返回
     * 基于暴力解法，可以借助二分查找提高效率
     *
     * @param piles 香蕉堆
     * @param H     小时
     * @return 最小速度
     */
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1, right = getMax(piles) + 1;
        while (left < right) {
            // 防止溢出
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, H)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canFinish(int[] piles, int speed, int h) {
        int time = 0;
        for (int pile : piles) {
            time += timeOf(pile, speed);
        }
        return time <= h;
    }

    private int timeOf(int pile, int speed) {
        return (pile / speed) + ((pile % speed) > 0 ? 1 : 0);
    }

    private int getMax(int[] piles) {
        int max = 1;
        for (int num : piles) {
            max = Math.max(max, num);
        }
        return max;
    }
}
