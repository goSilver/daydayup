package com.silver.labuladong.backtrack.dfs;

import java.util.LinkedList;
import java.util.List;

/**
 * 输入一个不包含重复数字的数组，要求算法输出这些数字的所有子集。
 *
 * @author csh
 * @date 2021/2/3
 **/
public class SubSets {

    private List<List<Integer>> res = new LinkedList<>();

    /**
     * 寻找所有子集
     *
     * @param nums 数组
     * @return 子集
     */
    private List<List<Integer>> subSets(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, 0, track);
        return res;
    }

    private void backtrack(int[] nums, int start, LinkedList<Integer> track) {
        res.add(track);
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1, track);
            track.removeLast();
        }
    }
}
