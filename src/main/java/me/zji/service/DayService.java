package me.zji.service;

import me.zji.entity.Day;

import java.util.Map;

/**
 * 工作日设置服务
 * Created by imyu on 2017/3/1.
 */
public interface DayService {
    /**
     * 创建一条记录
     * @param day
     */
    void create(Day day);

    /**
     * 通过日期查询
     * @param day
     * @return
     */
    Day queryByDay(String day);

    /**
     * 创建或更新
     * @param day
     */
    void createOrUpdate(Day day);
}
