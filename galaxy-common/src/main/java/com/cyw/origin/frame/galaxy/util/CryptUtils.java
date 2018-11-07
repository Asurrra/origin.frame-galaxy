package com.cyw.origin.frame.galaxy.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author yiwen.chang
 * @version
 * @date 2018/3/19
 */
public class CryptUtils {
    public static final String key = "weimob.saas.ops";
    private static final String UTF_16LE = "UTF-16LE";
    private static final String secretKey = "DESede";
    private static final String Algorithm = "DESede/ECB/PKCS5Padding";

    public static byte[] MD5Encode(byte[] bytes) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            return md.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }

    }

    /**
     * 自定义一个key
     *
     * @param keyRule
     */
    public static SecretKey getKey(String keyRule) throws UnsupportedEncodingException {
        SecretKey key = null;
        byte[] tmp = keyRule.getBytes("ASCII");

        byte[] keyByte = MD5Encode(tmp);
        // 创建一个空的八位数组,默认情况下为0
        byte[] byteTemp = new byte[24];
        // 将用户指定的规则转换成八位数组
        for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
            byteTemp[i] = keyByte[i];
        }
        key = new SecretKeySpec(byteTemp, secretKey);
        return key;
    }

    /**
     * 3DESECB加密
     *
     * @param src     要进行了加密的原文
     * @param keybyte 密钥 key必须是长度大于等于 3*8 = 24 位
     * @return
     * @throws Exception
     */
    public static String encryptMode(String src, String keybyte) {
        try {
            // 生成密钥
            SecretKey deskey = getKey(keybyte);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] tmp = c1.doFinal(src.getBytes(UTF_16LE));
            String res = Base64.encodeBase64String(tmp);
            return res;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 3DESECB解密
     *
     * @param src 要解密的密文字符
     * @param key 解密的Key key必须是长度大于等于 3*8 = 24 位
     * @return
     * @throws Exception
     */
    public static String decryptDES(String src, String key) throws Exception {
        // --通过base64,将字符串转成byte数组
        byte[] bytesrc = Base64.decodeBase64(src);
        // --解密的key
        SecretKey securekey = getKey(key);
        // --Chipher对象解密
        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        byte[] retByte = cipher.doFinal(bytesrc);

        return new String(retByte, UTF_16LE);
    }

}
