package me.zji.service;

import me.zji.dto.AdminUser;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 管理员用户服务
 * Created by imyu on 2017/2/23.
 */
public interface AdminUserService {
    /**
     * 通过用户名查找
     * @param username
     * @return
     */
    AdminUser queryByUsername(String username);

    /**
     * 创建管理员用户
     * @param param
     */
    void createByMap(Map param) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 更新管理员用户
     * @param param
     */
    void updateByMap(Map param);

    /**
     * 通过用户名删除管理员用户
     * @param username
     */
    void deleteByUsername(String username);
}
