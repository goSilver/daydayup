package com.silver.leetcode.hot100.q31_q40;

import java.util.HashSet;
import java.util.Set;

/**
 * @author csh
 * @date 2021/6/26
 **/
public class q128_LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : nums) {
            if (!numSet.contains(num - 1)) {
                int curNumber = num;
                int curStreak = 1;
                while (numSet.contains(curNumber + 1)) {
                    curNumber += 1;
                    curStreak += 1;
                }
                longestStreak = Math.max(longestStreak, curStreak);
            }
        }
        return longestStreak;
    }
}
