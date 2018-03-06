package me.zji.utils;

import me.zji.security.PasswordUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 我的测试类
 * Created by imyu on 2017/2/23.
 */
public class MyTest {
    @Test
    public void testHashMapGet() {
        Map map = new HashMap();
        map.put("xx","true");
        System.out.println("true".equals(map.get("xx")));
    }

    @Test
    public void testPassword() {
        try{
            String a = PasswordUtils.encryptPassword("");
            System.out.println(a);
        } catch (Exception e) {

        }

    }

    @Test
    public void tolower() {
        System.out.println("DAYINIT\n".toLowerCase() +
                "RECEIVEMARKET\n".toLowerCase() +
                "STARTTUXEDO\n".toLowerCase() +
                "DOWNTUXEDO\n".toLowerCase() +
                "CHECKDATA\n".toLowerCase() +
                "EXPREQUEST\n".toLowerCase() +
                "IMPORTDATA\n".toLowerCase() +
                "DEALDATA\n".toLowerCase() +
                "LIQCARRYOVER\n".toLowerCase());
    }

    @Test
    public void testddd() {
        String sss = "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `vc_tacode` varchar(8) NOT NULL COMMENT 'ta编号',\n" +
                "  `vc_taacco` varchar(12) NOT NULL COMMENT '基金账号' ,\n" +
                "  `vc_productcode` varchar(6) NOT NULL COMMENT '产品代码',\n" +
                "  `c_businflag` varchar(3) NOT NULL COMMENT '业务代码【020：认购，022：申购，024：赎回】',\n" +
                "  `c_status` char(1) NOT NULL COMMENT '状态【0：未导给ta，1：成功，2：失败】',\n" +
                "  `vc_serialno` varchar(18) NOT NULL COMMENT '流水编号',\n" +
                "  `vc_occurdate` varchar(10) NOT NULL COMMENT '发生时间',";
        Pattern pattern = Pattern.compile("(?<=`)\\w+(?=`)");
        Matcher matcher = pattern.matcher(sss);
        while (matcher.find()) {
            System.out.print(matcher.group() +", ");
        }
    }

    @Test
    public void print(){
        System.out.println(" `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `vc_tacode` varchar(8) NOT NULL COMMENT 'ta编号',\n" +
                "  `vc_taacco` varchar(12) NOT NULL COMMENT '基金账号' ,\n" +
                "  `vc_productcode` varchar(6) NOT NULL COMMENT '产品代码',\n" +
                "  `c_businflag` varchar(3) NOT NULL COMMENT '业务代码【020：认购，022：申购，024：赎回】',\n" +
                "  `c_status` char(1) NOT NULL COMMENT '状态【0：未导给ta，1：成功，2：失败】',\n" +
                "  `vc_serialno` varchar(18) NOT NULL COMMENT '客户编号',\n" +
                "  `vc_occurdate` varchar(10) NOT NULL COMMENT '发生时间',");
    }
    @Test
    public void dsf(){
        Map<String, String> buyMap = new HashMap<String, String>();
        String src = "taCode=44444444,taAcco=000000000000,productCode=22,businFlag=020,serialNo=000000000000000000,occurDate=2017-03-10,tradeAcco=00000000000000000,moneyType=978,balance=1.0,share=null,status=0";
        String[] splitArray = src.split(",");
        for (String couple : splitArray) {
            String[] items = couple.split("=");
            buyMap.put(items[0].trim(), items[1].trim());
        }
        System.out.println(buyMap.get("balance"));
        System.out.println(Double.valueOf(""));
    }
}
