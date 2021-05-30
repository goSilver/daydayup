package com.silver.leetcode.hot100.q01_q10;

/**
 * 盛最多水的容器
 *
 * @author csh
 * @date 2021/5/30
 **/
public class Q011_MaxArea {
    /**
     * 双指针
     * 思路：双指针遍历，找到最大值
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            // 容量 = 最小高度 * 间距
            int area = Math.min(height[l], height[r]) * (r - l);
            // 更新最大值
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }
}
