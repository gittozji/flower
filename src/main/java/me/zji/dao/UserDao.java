package me.zji.dao;

import me.zji.entity.User;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 用户接口 Dao
 * Created by qian yun on 2018/3/9.
 */

public interface UserDao {
    User getUserById(Long id);


    /**
     * 新增一条记录
     * @param user
     */
    void create(User user);

    /**
     * 通过用户名删除
     * @param username
     */
    void deleteByUsername(String username);

    /**
     * 更新一条记录
     * @param user
     */
    void update(User user);

    /**
     * 通过用户名查询
     * @param username
     * @return
     */
    User queryByUsername(String username);
}
