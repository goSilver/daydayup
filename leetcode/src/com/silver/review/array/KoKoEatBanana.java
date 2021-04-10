package com.silver.review.array;

/**
 * coco吃香蕉
 *
 * @author csh
 * @date 2021/4/10
 */
public class KoKoEatBanana {
    private int minEatingSpeed(int[] piles, int H) {
        int left = 1, right = getMax(piles);
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, H)) {
                right = mid;
            } else {
                left = right;
            }
        }
        return left;
    }

    private boolean canFinish(int[] piles, int speed, int h) {
        int time = 0;
        for (int num : piles) {
            time += timeOf(num, speed);
        }
        return time <= h;
    }

    private int timeOf(int num, int speed) {
        return num / speed + (num % speed > 0 ? 1 : 0);
    }

    private int getMax(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int num : piles) {
            max = Math.max(max, num);
        }
        return max;
    }
}
