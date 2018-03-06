package me.zji.dao;

import me.zji.entity.DealProcess;

import java.util.List;

/**
 * 流程控制 Dao
 * Created by imyu on 2017/2/26.
 */
public interface DealProcessDao {
    /**
     * 读取流程表
     * @return
     */
    List<DealProcess> queryAll();

    /**
     * 更新一条记录
     * @param dealProcess
     */
    void update(DealProcess dealProcess);

    /**
     * 流程全部置为未操作
     */
    void setInit();
}
