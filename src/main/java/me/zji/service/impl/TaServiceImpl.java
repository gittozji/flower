package me.zji.service.impl;

import me.zji.dao.TaDao;
import me.zji.entity.Ta;
import me.zji.service.TaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by imyu on 2017/3/1.
 */
@Service
public class TaServiceImpl implements TaService {
    @Autowired
    TaDao taDao;
    public void create(Ta ta) {
        taDao.create(ta);
    }

    public Ta queryByCode(String code) {
        return taDao.queryByCode(code);
    }
}
