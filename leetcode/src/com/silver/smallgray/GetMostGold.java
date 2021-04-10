package com.silver.smallgray;

/**
 * 国王与金矿
 * https://mp.weixin.qq.com/s/3h9iqU4rdH3EIy5m6AzXsg
 *
 * @author csh
 * @date 2021/4/10
 */
public class GetMostGold {

    public int getMostGold(int n, int w, int[] gold, int[] people) {
        int[] preResult = new int[people.length];
        int[] result = new int[people.length];

        // 填充边界格子
        for (int i = 0; i < n; i++) {
            if (i < people[0] ) {
                preResult[i] = 0;
            } else {
                preResult[i] = gold[0];
            }
        }

        // 填充剩余格子，外层循环是金矿数量，内层循环是工人数量
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < w; j++) {
                if (j < people[i]) {
                    result[j] = preResult[j];
                } else {
                    result[j] = Math.max(preResult[j], preResult[j - people[i]]+ gold[i]);
                }
            }
            preResult = result;
        }
        return result[n];
    }
}
