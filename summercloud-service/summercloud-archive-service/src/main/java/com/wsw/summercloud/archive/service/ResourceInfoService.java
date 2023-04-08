package com.wsw.summercloud.archive.service;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import com.wsw.summercloud.archive.mapper.ResourceInfoMapper;
import com.wsw.summercloud.archive.mapstruct.IResourceInfoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/5 20:04
 */
@Slf4j
@Service
public class ResourceInfoService extends ServiceImpl<ResourceInfoMapper, ResourceInfoEntity> {
    @Transactional
    public void saveOrUpdateResourceInfos(List<ResourceMsg> resourceMsgs) {
        List<ResourceInfoEntity> resourceInfoEntities = IResourceInfoConverter.INSTANCE.resourceMsgToResourceInfoEntity(resourceMsgs);
        saveOrUpdateBatch(resourceInfoEntities);
    }

    public void updateResourceInfoArchiveStatus(List<ResourceMsg> resourceMsgs) {
        new LambdaUpdateChainWrapper<>(this.getBaseMapper())
                .in(ResourceInfoEntity::getResourceId, resourceMsgs.stream().map(ResourceMsg::getResourceId).toArray())
                .set(ResourceInfoEntity::getArchiveStatus, 1)
                .update();
    }
}
