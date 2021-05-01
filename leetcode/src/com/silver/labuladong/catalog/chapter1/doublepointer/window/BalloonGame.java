package com.silver.labuladong.catalog.chapter1.doublepointer.window;

import java.util.HashMap;

/**
 * 【气球游戏】
 * 小Q在进行射击气球的游戏，如果小Q在连续T枪中打爆了所有颜色的气球，将得到一只QQ公仔作为奖励。（每种颜色的气球至少被打爆一只）。
 * 这个游戏中有m种不同颜色的气球，编号1到m。小Q一共有n发子弹，然后连续开了n枪。小Q想知道在这n枪中，打爆所有颜色的气球最少用了连续几枪？
 * 输入描述：
 * 第一行两个空格间隔的整数数n，m。n<=1000000 m<=2000
 * 第二行一共n个空格间隔的整数，分别表示每一枪打中的气球的颜色,0表示没打中任何颜色的气球。
 * 输出描述：
 * 一个整数表示小Q打爆所有颜色气球用的最少枪数。如果小Q无法在这n枪打爆所有颜色的气球，则输出-1
 * 示例
 * 输入：
 * 12 5
 * 2 5 3 1 3 2 4 1 0 5 4 3
 * 输出：
 * 6
 *
 * test
 * 12 5
 * 2 5 3 1 3 2 4 1 5 0 4 3
 * 5
 * @author csh
 * @date 2021/5/1
 */
public class BalloonGame {
    private Integer balloonGame(int n, int m, int[] balloon) {
        // 定义窗口和need
        HashMap<Integer, Integer> need = new HashMap<>();
        HashMap<Integer, Integer> window = new HashMap<>();
        // m种颜色的气球
        for (int i = 1; i <= m; i++)
            need.put(i, 1);
        // 控制窗口用的指针
        int left = 0, right = 0;
        // window中满need条件的个数
        int valid = 0;
        int res = Integer.MAX_VALUE;
        while (right < n) {
            // 向右扩大窗口
            int num = balloon[right];
            right++;
            if (need.containsKey(num)) {
                window.put(num, window.getOrDefault(num, 0) + 1);
                if (window.get(num).equals(need.get(num))) valid++;
            }

            while (valid == m) {
                // 记录最小窗口
                res = Math.min(res, right - left);
                // 缩小窗口
                int num2 = balloon[left];
                left++;
                if (need.containsKey(num2)) {
                    if (window.get(num2).equals(need.get(num2))) valid--;
                    window.put(num2, window.getOrDefault(num2, 0) - 1);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static void main(String[] args) {
        int[] balloon = new int[]{2, 5, 3, 1, 3, 2, 4, 1, 0, 5, 4, 3}; // 答案：6
        int[] balloon2 = new int[]{2, 5, 3, 1, 3, 2, 4, 1, 5, 0, 4, 3}; // 答案：5
        BalloonGame main = new BalloonGame();
        Integer res = main.balloonGame(12, 5, balloon2);
        System.out.print("最少需要：" + res);
    }
}
