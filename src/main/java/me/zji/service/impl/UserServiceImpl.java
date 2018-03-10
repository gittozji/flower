package me.zji.service.impl;

import me.zji.dao.RoleDao;
import me.zji.dao.UserDao;
import me.zji.dao.UserDetailDao;
import me.zji.dao.UserRoleDao;
import me.zji.entity.Role;
import me.zji.entity.User;
import me.zji.entity.UserDetail;
import me.zji.entity.UserRole;
import me.zji.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 * Created by imyu on 2017/2/11.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserDetailDao userDetailDao;
    public User getUser(Long id) {
        User user = userDao.getUserById(id);
        UserDetail userDetail = userDetailDao.selectByUserId(id);
        userDetail = userDetail == null ? new UserDetail() : userDetail;
        user.setUserDetail(userDetail);
        return user;
    }
    public User queryByUsername(String username) {
        return userDao.queryByUsername(username);
    }

    /**
     * 创建一条记录
     *
     * @param user
     */
    public void create(User user) {
        // 创建用户
        userDao.create(user);
    }

    public UserDetail queryByUserId(Long id) {
        return userDetailDao.selectByUserId(id);
    }

    public void update(User user, UserDetail detail) {
        userDao.update(user);
        if (userDetailDao.selectByUserId(detail.getUserId()) == null) {
            userDetailDao.create(detail);
        }
        userDetailDao.update(detail);

    }
}
