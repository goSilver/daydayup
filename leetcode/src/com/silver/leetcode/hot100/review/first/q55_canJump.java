package com.silver.leetcode.hot100.review.first;

/**
 * 跳跃游戏
 *
 * @author csh
 * @date 2021/7/4
 **/
public class q55_canJump {
    public boolean canJump(int[] nums) {
        int right = 0;
        for (int i = 0; i <= right; i++) {
            right = Math.max(right, i + nums[i]);
            if (right >= nums.length - 1) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        q55_canJump main = new q55_canJump();
        int[] arr = new int[]{0, 1};
        boolean b = main.canJump(arr);
        System.out.println(b);
    }

}
