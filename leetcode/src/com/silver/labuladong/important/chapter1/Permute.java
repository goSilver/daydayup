package com.silver.labuladong.important.chapter1;

import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 * @author csh
 * @date 2021/5/24
 **/
public class Permute {
    private List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
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
            if (track.contains(num)) continue;
            track.add(num);
            backtrack(nums, track);
            track.removeLast();
        }
    }
}
