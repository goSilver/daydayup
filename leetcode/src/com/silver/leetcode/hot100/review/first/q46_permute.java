package com.silver.leetcode.hot100.review.first;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author csh
 * @date 2021/7/4
 **/
public class q46_permute {

    private List<List<Integer>> res = new LinkedList<>();
    private boolean[] vis;

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, track);
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]))
                continue;
            track.add(nums[i]);
            vis[i] = true;
            backtrack(nums, track);
            vis[i] = false;
            track.removeLast();
        }
    }
}
