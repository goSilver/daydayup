package com.silver.medium.q002;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Chen ShaoHua
 * @date 2020/6/3
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

}

class Solution2 {

    public static void main(String[] args) {
//        Map<String, Integer> map = new HashMap();
//        map.put("aaa", 1);
//        map.put("bbb", 1);
//        map.put("ccc", 0);
//
//        map = map.entrySet().stream()
//                .filter(o -> o.getValue().equals(1))
//                .collect(Collectors.toMap((e) -> (String) e.getKey(),
//                        (e) -> e.getValue()));
        BigDecimal a = new BigDecimal("-1.00");
        BigDecimal b = new BigDecimal("-1.00");
        System.out.println(a.equals(b));
    }


}