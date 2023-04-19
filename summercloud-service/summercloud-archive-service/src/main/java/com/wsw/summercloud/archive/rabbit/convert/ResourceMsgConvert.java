package com.wsw.summercloud.archive.rabbit.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsw.summercloud.api.msg.ResourceMsg;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 资源消息转换
 * @Author: wangsongwen
 * @Date: 2023/4/19 22:54
 */
public class ResourceMsgConvert {
    public static List<ResourceMsg> convert(List<String> messageBodys) {
        List<ResourceMsg> resourceMsgs = new ArrayList<>();
        resourceMsgs = messageBodys.stream().map(e -> {
            try {
                return new ObjectMapper().readValue(e, ResourceMsg.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException("转换异常: " + ex);
            }
        }).collect(Collectors.toList());
        return resourceMsgs;
    }
}
