package com.silver.review.array;

import java.util.LinkedList;
import java.util.List;

/**
 * 回溯
 *
 * @author csh
 * @date 2021/4/13
 */
public class Backtrack {

    private List<List<Integer>> res = new LinkedList<>();

    /**
     * 组合
     */
    private List<List<Integer>> combine(int n, int k) {
        if (n<0 || k<0)return res;
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(n,k,1,track);
        return res;
    }

    private void backtrack(int n, int k, int start, LinkedList<Integer> track) {
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i <= n; i++) {
            track.add(i);
            backtrack(n,k,i+1,track);
            track.removeLast();
        }
    }

    /**
     * 全排列
     */
    private List<List<Integer>> fullArrangement(int[] nums) {
        if (nums.length <= 0) return res;
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int num : nums) {
            if (track.contains(num))
                continue;
            track.add(num);
            backtrack(nums, track);
            track.removeLast();
        }
    }

    /**
     * 子集
     */
    private List<List<Integer>> subsets(int[] nums) {
        if (nums.length <= 0) return res;
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, 0, track);
        return res;
    }

    private void backtrack(int[] nums, int start, LinkedList<Integer> track) {
        res.add(new LinkedList<>(track));
        for (int i=start; i < nums.length ; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1, track);
            track.removeLast();
        }
    }
}
