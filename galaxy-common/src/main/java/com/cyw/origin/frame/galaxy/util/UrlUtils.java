package com.cyw.origin.frame.galaxy.util;

/**
 * 替换url占位符工具类
 * @author yiwen.chang
 * @version 1.3.0
 * @date 2018/1/22
 */
public class UrlUtils {

    public static String replaceHolder(String url, Long wid, Long pid, Long storeId) {
        String result = url;
        String widstr = wid == null ? "":wid.toString();
        String pidstr = pid == null ? "":pid.toString();
        String storeIdstr = storeId == null ? "":storeId.toString();
        if (url != null) {

            result = url.replace("{wid}", widstr);
            result = result.replace("{pid}", pidstr);
            result = result.replace("{storeId}", storeIdstr);
        }

        return result;
    }

}
