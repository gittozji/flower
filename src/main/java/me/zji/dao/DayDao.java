package me.zji.dao;

import me.zji.entity.Day;

/**
 * 工作日设置 Dao
 * Created by imyu on 2017/3/1.
 */
public interface DayDao {
    /**
     * 增加一条记录
     * @param day
     */
    void create(Day day);

    /**
     * 通过日期查询
     * @param day
     */
    Day queryByDay(String day);

    void update(Day day);
}
