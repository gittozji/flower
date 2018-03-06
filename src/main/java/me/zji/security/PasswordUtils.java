package me.zji.security;

import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码工具
 * Created by imyu on 2017/2/14.
 */
public class PasswordUtils {
    public static String encryptPassword(String src) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String encryptSrc=base64en.encode(md5.digest(src.getBytes("utf-8")));
        return encryptSrc;
    }
}
