package com.cyw.origin.frame.galaxy.util;

/**
 *
 * @author yiwen.chang
 * @version
 * @date 2018/3/19
 */
public class CharsetUtil {

    /**
     * 中文占两个字符,统计字符串长度
     * 
     * @param str
     * @return
     */
    public static int realLength(String str) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < str.length(); i++) {
            String temp = str.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

}
