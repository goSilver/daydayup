package com.silver.leetcode.hot100.q11_q20;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author csh
 * @date 2021/6/24
 **/
public class q46_Permute {
    private List<List<Integer>> res = new LinkedList<>();
    private boolean[] vis;
    /**
     * 主函数，输入一组不重复的数字，返回它们的全排列
     *
     * @param nums 数组
     * @return 全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        vis = new boolean[nums.length];
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
            // 排除不合法的选择
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
}
