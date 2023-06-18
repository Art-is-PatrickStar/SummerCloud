package com.wsw.summercloud.archive.service.impl;

import com.wsw.summercloud.api.dto.ArchiveNodeResponseDto;
import com.wsw.summercloud.api.dto.ResourceInfoRequestDto;
import com.wsw.summercloud.api.dto.TaskJobRequestDto;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.client.TaskClient;
import com.wsw.summercloud.archive.mapstruct.IResourceInfoConverter;
import com.wsw.summercloud.archive.service.ArchiveNodeService;
import com.wsw.summercloud.archive.service.ResourceInfoService;
import com.wsw.summercloud.archive.service.ResourceMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/6 22:59
 */
@Slf4j
@Service
public class ResourceMsgServiceImpl implements ResourceMsgService {
    @Autowired
    private TaskClient taskClient;
    @Autowired
    private ArchiveNodeService archiveNodeService;
    @Autowired
    private ResourceInfoService resourceInfoService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    public void realTimeResourceHandle(List<ResourceMsg> resourceMsgs) {
        List<ResourceInfoRequestDto> resourceInfoRequestDtos = IResourceInfoConverter.INSTANCE.resourceMsgToRequestDto(resourceMsgs);
        resourceInfoService.saveOrUpdateResourceInfos(resourceInfoRequestDtos);
        executorService.execute(() -> doArchive(resourceInfoRequestDtos));
    }

    @Override
    public void historyResourceHandle(List<ResourceMsg> resourceMsgs) {
        List<ResourceInfoRequestDto> resourceInfoRequestDtos = IResourceInfoConverter.INSTANCE.resourceMsgToRequestDto(resourceMsgs);
        executorService.execute(() -> doArchive(resourceInfoRequestDtos));
    }

    private void doArchive(List<ResourceInfoRequestDto> resourceInfoRequestDtos) {
        try {
            Map<Long, List<TaskJobRequestDto>> archivedResource = getArchivedResource(resourceInfoRequestDtos);
            archivedResource.values().forEach(taskClient::createTask);
        } catch (Exception e) {
            throw new RuntimeException("归档创建任务失败: " + e);
        }
        List<Long> resourceIds = resourceInfoRequestDtos.stream().map(ResourceInfoRequestDto::getResourceId).collect(Collectors.toList());
        resourceInfoService.updateResourceInfoArchiveStatus(resourceIds);
    }

    private Map<Long, List<TaskJobRequestDto>> getArchivedResource(List<ResourceInfoRequestDto> resourceInfoRequestDtos) {
        Map<Long, List<TaskJobRequestDto>> archivedResourcesMap = new HashMap<>();
        List<TaskJobRequestDto> requestDtos = IResourceInfoConverter.INSTANCE.requestDtoToTaskJobRequestDto(resourceInfoRequestDtos);
        List<ArchiveNodeResponseDto> archiveNodes = archiveNodeService.getAllArchiveNodes().stream().filter(t -> t.getEnable() == 1).collect(Collectors.toList());
        for (ArchiveNodeResponseDto archiveNode : archiveNodes) {
            List<TaskJobRequestDto> archivedResources = new ArrayList<>();
            String archiveRule = archiveNode.getArchiveRule();
            for (TaskJobRequestDto requestDto : requestDtos) {
                //TODO: 根据归档规则获取命中的资源
                requestDto.setArchiveId(archiveNode.getArchiveId());
                archivedResources.add(requestDto);
            }
            archivedResourcesMap.put(archiveNode.getArchiveId(), archivedResources);
        }
        return archivedResourcesMap;
    }
}
