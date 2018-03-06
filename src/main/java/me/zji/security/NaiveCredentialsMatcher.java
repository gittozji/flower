package me.zji.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.stereotype.Component;


/**
 * 自己实现的用户凭证匹配器
 * 比SimpleCredentialsMatcher还要Simple
 * Created by imyu on 2017/2/14.
 */
@Component
public class NaiveCredentialsMatcher implements CredentialsMatcher {
    private static Log log = LogFactory.getLog(NaiveCredentialsMatcher.class);
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        try{
            // token 密码加密
            char[] tokenPassword = (char[]) token.getCredentials();
            String encryptPassword = PasswordUtils.encryptPassword(String.valueOf(tokenPassword));
            // 密码匹配
            String infoPassword = String.valueOf((String) info.getCredentials());
            if(encryptPassword.equals(infoPassword)) {
                return true;
            } else{
                return false;
            }
        }catch (Exception e) {
            log.error("密码匹配异常", e);
        }
        return false;
    }
}
