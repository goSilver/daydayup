package com.silver.labuladong.catalog.chapter1.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author csh
 * @date 2021/5/1
 */
public class FullArrange {
    public static void main(String[] args) {
        FullArrange main = new FullArrange();
        List<List<Integer>> permute = main.permute(new int[]{1, 2, 3});
        System.out.print(permute);
    }
    private List<List<Integer>> res = new LinkedList<>();

    private List<List<Integer>> permute(int[] nums) {
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
