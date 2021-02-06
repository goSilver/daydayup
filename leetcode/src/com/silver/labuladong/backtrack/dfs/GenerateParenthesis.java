package com.silver.labuladong.backtrack.dfs;

import java.util.LinkedList;
import java.util.List;

/**
 * 括号生成：
 * 输入是一个正整数n，输出是n对儿括号的所有合法组合
 *
 * @author csh
 * @date 2021/2/6
 **/
public class GenerateParenthesis {

    /**
     * 括号生成
     *
     * @param n n对括号
     * @return 所有合法组合
     */
    public List<String> generateParenthesis(int n) {
        LinkedList<String> res = new LinkedList<>();
        if (n == 0) return res;
        StringBuilder track = new StringBuilder();
        backtrack(n, n, track, res);
        return res;
    }

    /**
     * 回溯
     *
     * @param left  左括号数量
     * @param right 右括号数量
     * @param track 走过的路径
     * @param res   所有组合
     */
    private void backtrack(int left,
                           int right,
                           StringBuilder track,
                           LinkedList<String> res) {
        // 如果右括号数量小于左括号数量，不合法，直接返回
        if (right < left) return;
        // 如果左右括号其一数量小于零，不合法，直接返回
        if (left < 0 || right < 0) return;
        // base case 如果左右括号恰好都用完了，则找到一种解法
        if (left == 0 && right == 0) {
            res.add(track.toString());
            return;
        }
        // 尝试放一个左括号
        track.append("(");
        backtrack(left - 1, right, track, res);
        track.deleteCharAt(track.length() - 1);
        // 尝试放一个右括号
        track.append(")");
        backtrack(left, right - 1, track, res);
        track.deleteCharAt(track.length() - 1);
    }
}
