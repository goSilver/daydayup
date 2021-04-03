package com.silver.labuladong.backtrack.dfs;

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

    /**
     * 主函数，输入一组不重复的数字，返回它们的全排列
     *
     * @param nums 数组
     * @return 全排列
     */
    private List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
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

        for (int num : nums) {
            // 排除不合法的选择
            if (track.contains(num))
                continue;
            // 做选择
            track.add(num);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
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
