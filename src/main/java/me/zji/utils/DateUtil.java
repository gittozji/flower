package me.zji.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间赋值工具
 * Created by imyu on 2017/3/9.
 */
public class DateUtil {
    public static String getNowDate() {
        String date = "0000-00-00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.format(new Date());
        } catch (Exception e){}
        return date;
    }
}
