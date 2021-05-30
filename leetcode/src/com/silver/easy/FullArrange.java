package com.silver.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * @author csh
 * @date 2021/5/27
 **/
public class FullArrange {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        FullArrange main = new FullArrange();
        List<List<Integer>> lists = main.fullArrange(arr);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + "");
            }
            System.out.println();
        }
    }

    private List<List<Integer>> res = new LinkedList<>();
    private Integer index = 0;
    private List<List<Integer>> fullArrange(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track, 0);
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track, int index) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
        }

        for (int num : nums) {
//            if (track.contains(num)) continue;
            track.add(num);
            backtrack(nums, track, index + 1);
            track.removeLast();
        }

    }
}
