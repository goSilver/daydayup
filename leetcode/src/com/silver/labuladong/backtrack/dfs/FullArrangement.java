package com.silver.labuladong.backtrack.dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入一组不重复的数字，返回它们的全排列
 *
 * @author csh
 * @date 2021/2/2
 **/
public class FullArrangement {

    public static void main(String[] args) {
        FullArrangement arrangement = new FullArrangement();
        int[] arr = {1,2,3};
        List<List<Integer>> res = arrangement.permute(arr);
        for (List<Integer> re : res) {
            for (Integer integer : re) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    private List<List<Integer>> res = new LinkedList<>();
    private boolean[] vis;
    /**
     * 主函数，输入一组不重复的数字，返回它们的全排列
     *
     * @param nums 数组
     * @return 全排列
     */
    private List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        vis = new boolean[nums.length];
        // 对原数组排序，保证相同的数字都相邻
        Arrays.sort(nums);
        backtrack(nums, track);
        return res;
    }

    /**
     * 回溯：
     * 路径：记录在 track 中
     * 选择列表：nums 中不存在于 track 的那些元素
     * 结束条件：nums 中的元素全都在 track 中出现
     *
     * @param nums 选择列表
     * @param track 路径
     */
    private void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            /*
             * 排除不合法的选择
             * 加上 !vis[i - 1]来去重主要是通过限制一下两个相邻的重复数字的访问顺序
             * 对原数组排序，保证相同的数字都相邻，然后每次填入的数一定是这个数所在重复数集合中「从左往右第一个未被填过的数字」
             */
            if (vis[i] || (i>0 && nums[i] == nums[i-1]&& !vis[i-1]))
                continue;
            // 做选择
            track.add(nums[i]);
            vis[i] = true;
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            vis[i] = false;
            track.removeLast();
        }
    }

    // 默写
    private List<List<Integer>> myPermute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    private void myBacktrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int num : nums) {
            if (track.contains(num)) {
                continue;
            }
            track.add(num);
            backtrack(nums, track);
            track.removeLast();
        }
    }
}
