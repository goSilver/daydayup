package com.silver.leetcode.string;

import java.net.Inet6Address;
import java.net.InetAddress;

/**
 * 验证IP地址
 *
 * @author csh
 * @date 2021/3/20
 **/
public class Q468ValidIPAddress {

    /**
     * 使用内置函数和 try/catch 结构检查 IP 地址的正确性
     *
     * @param IP
     * @return
     */
    public String validIPAddress(String IP) {
        try {
            return (InetAddress.getByName(IP) instanceof Inet6Address) ? "IPv6": "IPv4";
        } catch(Exception e) {}
        return "Neither";
    }

}
