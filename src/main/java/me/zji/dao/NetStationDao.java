package me.zji.dao;

import me.zji.entity.NetStation;

/**
 * 网点信息 DAO
 * Created by imyu on 2017/3/1.
 */
public interface NetStationDao {
    /**
     * 创建一条记录
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
