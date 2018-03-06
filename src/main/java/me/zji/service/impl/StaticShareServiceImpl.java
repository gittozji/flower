package me.zji.service.impl;

import me.zji.dao.StaticShareDao;
import me.zji.entity.StaticShare;
import me.zji.service.StaticShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 静态份额服务
 * Created by imyu on 2017/3/14.
 */
@Service
public class StaticShareServiceImpl implements StaticShareService {
    @Autowired
    StaticShareDao staticShareDao;
    public void createOrUpdate(StaticShare staticShare) {
        if (staticShareDao.queryByCodeAndAcco(staticShare) == null) {
            staticShareDao.create(staticShare);
        } else {
            staticShareDao.update(staticShare);
        }
    }

    public StaticShare queryByCodeAndAcco(StaticShare staticShare) {
        return staticShareDao.queryByCodeAndAcco(staticShare);
    }
}
