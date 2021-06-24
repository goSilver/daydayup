package com.silver.leetcode.hot100.q11_q20;

/**
 * 跳跃游戏
 * @author csh
 * @date 2021/6/24
 **/
public class q55_CanJump {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 1, 4};
        q55_CanJump main = new q55_CanJump();
        boolean b = main.canJump(arr);
//        int b = 0;
//        System.out.println(b++);
        System.out.println(b);
    }

    public boolean canJump(int[] arr) {
        int n = arr.length;
        int rightMost = 0;
        for (int i = 0; i < n; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, arr[i] + i);
                if (rightMost >= n - 1) return true;
            }
        }
        return false;
    }
}
