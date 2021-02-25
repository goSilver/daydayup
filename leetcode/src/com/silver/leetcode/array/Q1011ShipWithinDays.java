package com.silver.leetcode.array;

/**
 * 在 D 天内送达包裹的能力
 *
 * @author csh
 * @date 2021/2/25
 **/
public class Q1011ShipWithinDays {
    public int shipWithinDays(int[] weights, int D) {
        int left = getMax(weights);
        int right = getSum(weights);
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(weights, mid, D)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canFinish(int[] weights, int cap, int d) {
        int i = 0;
        for (int day = 0; day < d; day++) {
            int maxCap = cap;
            while ((maxCap -= weights[i]) >= 0) {
                i++;
                if (i == weights.length) return true;
            }
        }
        return false;
    }

    private int getSum(int[] weights) {
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
        }
        return sum;
    }

    private int getMax(int[] weights) {
        int max = 0;
        for (int weight : weights) {
            max = Math.max(max, weight);
        }
        return max;
    }
}
