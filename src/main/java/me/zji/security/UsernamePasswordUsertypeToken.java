package me.zji.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 用户名密码类型Token
 * Created by imyu on 2017/2/12.
 */
public class UsernamePasswordUsertypeToken extends UsernamePasswordToken {
    /**
     * 用户类型
     */
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public UsernamePasswordUsertypeToken(final String username, final String password,final Integer type) {
        super(username, password);
        this.type = type;
    }

    @Override
    public void clear() {
        super.clear();
        this.type = null;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        superString += ", type=" + this.type;
        return superString;
    }
}
