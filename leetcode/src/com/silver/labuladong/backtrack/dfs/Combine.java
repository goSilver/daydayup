package com.silver.labuladong.backtrack.dfs;

import java.util.LinkedList;
import java.util.List;

/**
 * 组合：
 * 输入两个数字 n, k，算法输出 [1..n] 中 k 个数字的所有组合。
 *
 * @author csh
 * @date 2021/2/6
 **/
public class Combine {

    public static void main(String[] args) {
        Combine combine = new Combine();
        List<List<Integer>> res = combine.combine(3, 2);
        System.out.println(res.toString());
    }

    private List<List<Integer>> res = new LinkedList<>();

    /**
     * 主方法
     *
     * @param n 数字n
     * @param k 数字k
     * @return 所有组合
     */
    private List<List<Integer>> combine(int n, int k) {
        // base case
        if (n <= 0 || k <= 0) return res;
        // 记录走过的路径
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(n, k, 1, track);
        return res;
    }

    /**
     * 回溯
     *
     * @param n 数字n
     * @param k 数字k
     * @param start 起始下标
     * @param track 路径
     */
    private void backtrack(int n,
                           int k,
                           int start,
                           LinkedList<Integer> track) {
        // 到达树的底部
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i <= n; i++) {
            // 执行选择
            track.add(i);
            // 回溯
            backtrack(n, k, i + 1, track);
            // 撤销选择
            track.removeLast();
        }

    }
}
