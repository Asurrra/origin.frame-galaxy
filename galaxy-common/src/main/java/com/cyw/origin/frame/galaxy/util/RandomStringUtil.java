package com.cyw.origin.frame.galaxy.util;

import java.util.Random;

/**
 *
 * @author yiwen.chang
 * @version
 * @date 2018/3/19
 */
public class RandomStringUtil {

    private static final char[] SEEDS = "abcdefghjklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ123456789".toCharArray();
    private static final char[] SEEDS_NUM = "123456789".toCharArray();

    /**
     * 随机数字+字符
     * 
     * @param size size
     * @return
     */
    public static String random(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must > 0");
        }
        return random(SEEDS, size);
    }

    /**
     * 随机数组
     * 
     * @param size size
     * @return
     */
    public static String randomNum(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must > 0");
        }
        return random(SEEDS_NUM, size);
    }

    private static String random(char[] seed, int size) {
        char[] result = new char[size];
        for (int i = 0; i < size; i++) {
            result[i] = seed[new Random().nextInt(seed.length)];
        }
        return new String(result);
    }

}
