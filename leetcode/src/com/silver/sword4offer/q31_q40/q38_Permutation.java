package com.silver.sword4offer.q31_q40;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 字符串的排列
 * @author csh
 * @date 2021/6/20
 **/
public class q38_Permutation {

    private LinkedList<String> list = new LinkedList<>();
    private boolean[] vis;

    public static void main(String[] args) {
        q38_Permutation main = new q38_Permutation();
        String[] abcs = main.permutation("abb");
        System.out.println(Arrays.toString(abcs));

    }

    public String[] permutation(String s) {
        LinkedList<Character> track = new LinkedList<>();
        char[] chars = s.toCharArray();
        vis = new boolean[chars.length];
        Arrays.sort(chars);

        backtrack(chars, track);

        Object[] objects = list.toArray();
        String[] res = new String[objects.length];
        for (int i = 0; i < objects.length; i++) {
            res[i] = (String) objects[i];
        }
        return res;
    }


    private void backtrack(char[] chars, LinkedList<Character> track) {
        if (track.size() == chars.length) {
            StringBuilder sb = new StringBuilder();
            for (Character character : track) {
                sb.append(character);
            }
            list.add(sb.toString());
        }

        for (int i = 0; i < chars.length; i++) {
            if (vis[i] || (i > 0 && chars[i] == chars[i - 1] && !vis[i - 1]))
                continue;
            track.add(chars[i]);
            vis[i] = true;
            backtrack(chars, track);
            vis[i] = false;
            track.removeLast();
        }
    }
}
