package com.silver.sword4offer.q11_q20;

/**
 * 机器人的运动范围
 *
 * @author csh
 * @date 2021/2/7
 **/
public class q13 {

    public static void main(String[] args) {
        q13 q = new q13();
        int res = q.movingCount(16, 8, 4);
        System.out.println(res);
    }

    public int movingCount(int m, int n, int k) {
        int res=0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = get(i) + get(j);
                if (sum <= k) {
                    res++;
                } else {
                    break;
                }
            }
        }
        return res;
    }

    private int get(int x) {
        int s = 0;
        while(x != 0) {
            s += x % 10;
            x = x / 10;
        }
        return s;
    }
    public int movingCount2(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        boolean[][] vis = new boolean[m][n];
        int ans = 1;
        vis[0][0] = true;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i == 0 && j == 0) || get(i) + get(j) > k) {
                    continue;
                }
                // 边界判断
                if (i - 1 >= 0) {
                    vis[i][j] |= vis[i - 1][j];
                }
                if (j - 1 >= 0) {
                    vis[i][j] |= vis[i][j - 1];
                }
                ans += vis[i][j] ? 1 : 0;
            }
        }
        return ans;
    }

}
