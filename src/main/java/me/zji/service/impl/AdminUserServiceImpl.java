package me.zji.service.impl;

import me.zji.dao.AdminInfoDao;
import me.zji.dao.RoleDao;
import me.zji.dao.UserDao;
import me.zji.dao.UserRoleDao;
import me.zji.dto.AdminUser;
import me.zji.entity.AdminInfo;
import me.zji.entity.Role;
import me.zji.entity.User;
import me.zji.entity.UserRole;
import me.zji.security.PasswordUtils;
import me.zji.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 管理员用户服务
 * Created by imyu on 2017/2/23.
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    AdminInfoDao adminInfoDao;

    public AdminUser queryByUsername(String username) {
        User user = userDao.queryByUsername(username);
        if(user == null) {
            return null;
        }
        AdminUser adminUser = new AdminUser();
        AdminInfo adminInfo = adminInfoDao.queryByUsername(username);
        adminUser.setUsername(user.getUsername());
        adminUser.setNikename(user.getNikename());
        adminUser.setType(user.getType());
        adminUser.setNetno(adminInfo.getNetno());
        adminUser.setMobile(adminInfo.getMobile());
        adminUser.setEmail(adminInfo.getEmail());
        adminUser.setAddress(adminInfo.getAddress());
        return adminUser;
    }

    public void createByMap(Map param) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        /** 保存用户基本信息 */
        User user = new User();
        user.setUsername((String) param.get("username"));
        user.setNikename((String) param.get("nikeName"));
        user.setPassword(PasswordUtils.encryptPassword((String) param.get("password")));
        user.setType(1); // 管理员
        userDao.create(user);

        user = userDao.queryByUsername((String) param.get("username"));

        /** 保存管理员信息 */
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setUserName((String) param.get("username"));
        adminInfo.setNetno((String) param.get("netStation"));
        adminInfo.setMobile((String) param.get("mobile"));
        adminInfo.setEmail((String) param.get("email"));
        adminInfo.setAddress((String) param.get("address"));
        adminInfoDao.create(adminInfo);

        /** 保存权限管理记录 */
        if("true".equals(param.get("admin").toString())) {
            Role role = roleDao.queryByCode("admin");
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            userRoleDao.create(userRole);
        }
        if("true".equals(param.get("admin_trade").toString())) {
            Role role = roleDao.queryByCode("admin_trade");
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            userRoleDao.create(userRole);
        }
        if("true".equals(param.get("admin_process").toString())) {
            Role role = roleDao.queryByCode("admin_process");
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            userRoleDao.create(userRole);
        }
        if("true".equals(param.get("admin_maintain").toString())) {
            Role role = roleDao.queryByCode("admin_maintain");
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            userRoleDao.create(userRole);
        }
        if("true".equals(param.get("admin_user").toString())) {
            Role role = roleDao.queryByCode("admin_user");
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            userRoleDao.create(userRole);
        }

    }

    public void updateByMap(Map param) {

    }

    public void deleteByUsername(String username) {

    }
}
