package me.zji.service;

import me.zji.dao.NetStationDao;
import me.zji.entity.NetStation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 网点信息服务
 * Created by imyu on 2017/3/1.
 */
public interface NetStationService {
    /**
     * 新增一条记录
     * @param netStation
     */
    void create(NetStation netStation);

    /**
     * 通过网点编号查询
     * @param netNo
     * @return
     */
    NetStation queryByNetNo(String netNo);
}
