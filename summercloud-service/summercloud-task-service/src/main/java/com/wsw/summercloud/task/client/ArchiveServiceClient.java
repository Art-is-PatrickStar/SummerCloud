package com.wsw.summercloud.task.client;

import com.wsw.summercloud.api.basic.Result;
import com.wsw.summercloud.api.dto.ArchiveNodeResponseDto;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/20 17:21
 */
@HttpExchange(url = "/archive", accept = "application/json", contentType = "application/json")
public interface ArchiveServiceClient {
    @GetExchange("/getAllArchiveNodes")
    Result<List<ArchiveNodeResponseDto>> getAllArchiveNodes();
}