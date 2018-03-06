package me.zji.service;

import me.zji.entity.DealProcess;

import java.util.List;

/**
 * 流程控制服务
 * Created by imyu on 2017/2/26.
 */
public interface DealProcessService {
    /**
     * 获取当前系统流程
     * @return
     */
    List<DealProcess> getCurrentDealProcess();
    /**
     * 查询所有流程
     * @return
     */
    List<DealProcess> queryAll();
    /**
     * 更新流程
     * @param dealProcess
     */
    void update(DealProcess dealProcess);

    /**
     * 日初始化服务
     */
    void dayInit();

    boolean isTradeTime();
}
