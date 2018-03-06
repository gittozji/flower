package me.zji.service.impl;

import me.zji.dao.DayDao;
import me.zji.entity.Day;
import me.zji.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by imyu on 2017/3/1.
 */
@Service
public class DayServiceImpl implements DayService {
    @Autowired
    DayDao dayDao;
    public void create(Day day) {
        dayDao.create(day);
    }

    public Day queryByDay(String day) {
        return dayDao.queryByDay(day);
    }

    public void createOrUpdate(Day day) {
        if(dayDao.queryByDay(day.getDay()) == null) {
            dayDao.create(day);
        } else {
            dayDao.update(day);
        }
    }
}
