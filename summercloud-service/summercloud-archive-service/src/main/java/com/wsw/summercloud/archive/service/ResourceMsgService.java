package com.wsw.summercloud.archive.service;

import com.wsw.summercloud.api.msg.ResourceMsg;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/13 13:54
 */
public interface ResourceMsgService {
    /**
     * 实时数据处理
     *
     * @param resourceMsgs
     * @return void
     */
    void realTimeResourceHandle(List<ResourceMsg> resourceMsgs);

    /**
     * 历史数据处理
     *
     * @param resourceMsgs
     * @return void
     */
    void historyResourceHandle(List<ResourceMsg> resourceMsgs);
}
