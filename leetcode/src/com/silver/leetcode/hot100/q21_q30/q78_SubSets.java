package com.silver.leetcode.hot100.q21_q30;

import java.util.LinkedList;
import java.util.List;

/**
 * 子集
 *
 * @author csh
 * @date 2021/6/26
 **/
public class q78_SubSets {

    private List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length <= 0) return res;
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, 0, track);
        return res;
    }

    private void backtrack(int[] nums, int start, LinkedList<Integer> track) {
        res.add(new LinkedList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1, track);
            track.removeLast();
        }
    }
}
