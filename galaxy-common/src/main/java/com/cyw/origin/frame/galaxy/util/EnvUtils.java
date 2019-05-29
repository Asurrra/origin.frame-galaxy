package com.cyw.origin.frame.galaxy.util;

import com.cyw.origin.frame.galaxy.common.enums.EnvEnum;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EnvUtils {

    private static String ip;

    private static String hostName;

    private static EnvEnum env = null;

    static {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            hostName = InetAddress.getLocalHost().getHostName();
            System.out.println("Server Info: ip=" + ip + " hostName=" + hostName);
        } catch (UnknownHostException e) {
        }
    }

    public static EnvEnum getEnv() {
        if (env != null) {
            return env;
        }
        // 内网网环境
        if ("AP".equals(hostName)) {
            env = EnvEnum.LOCAL;
        } else if (ip.startsWith("192.") || ip.startsWith("172.")) {
            env = EnvEnum.DEV;
        } else if (hostName != null && hostName.indexOf("-online-") != -1) {
            env = EnvEnum.ONLINE;
        } else if (hostName != null && hostName.indexOf("-pl-") != -1) {
            env = EnvEnum.PL;
        } else if (hostName != null && hostName.indexOf("-qa-") != -1) {
            env = EnvEnum.PL;
        } else {
            env = EnvEnum.LOCAL;
        }
        return env;
    }

    public static String getIp() {
        return ip;
    }

    public static String getActiveProfiles() {
        EnvEnum env = EnvUtils.getEnv();
        switch (env) {
            case DEV:
                return "dev";
            case ONLINE:
                return "online";
            default:
                return "local";
        }
    }

}