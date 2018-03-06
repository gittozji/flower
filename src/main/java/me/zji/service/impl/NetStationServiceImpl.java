package me.zji.service.impl;

import me.zji.dao.NetStationDao;
import me.zji.entity.NetStation;
import me.zji.service.NetStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 网点信息服务
 * Created by imyu on 2017/3/1.
 */
@Service
public class NetStationServiceImpl implements NetStationService {
    @Autowired
    NetStationDao netStationDao;
    public void create(NetStation netStation) {
        netStationDao.create(netStation);
    }

    public NetStation queryByNetNo(String netNo) {
        return netStationDao.queryByNetNo(netNo);
    }
}
