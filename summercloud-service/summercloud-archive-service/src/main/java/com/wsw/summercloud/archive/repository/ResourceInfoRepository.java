package com.wsw.summercloud.archive.repository;

import com.wsw.summercloud.api.msg.ResourceMsg;
import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/17 17:35
 */
@Repository
public interface ResourceInfoRepository extends JpaRepository<ResourceInfoEntity, Long> {
    void updateResourceInfoArchiveStatus(List<ResourceMsg> resourceMsgs);
    //IPage<ResourceInfoEntity> selectResourceInfos(Page<?> page, @Param("query") ResourceInfoQueryDto queryDto);
}
