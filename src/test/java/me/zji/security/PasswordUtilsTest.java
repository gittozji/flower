package me.zji.security;

import org.junit.Test;

/**
 * Created by imyu on 2017/2/14.
 */
public class PasswordUtilsTest {
    @Test
    public void test() {
        char[] chars = {'1','2','3','4'};
        System.out.println(String.valueOf(chars));
        String s2 = chars.toString();
        System.out.println(s2);
        try{
            String s = PasswordUtils.encryptPassword("1234");
            System.out.println(s);
        }catch (Exception e) {
            System.out.println("dddd");
        }

    }
}
