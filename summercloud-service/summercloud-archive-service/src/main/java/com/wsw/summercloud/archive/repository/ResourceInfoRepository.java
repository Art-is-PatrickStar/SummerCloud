package com.wsw.summercloud.archive.repository;

import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/17 17:35
 */
@Repository
public interface ResourceInfoRepository extends JpaRepository<ResourceInfoEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "update resource_info set archive_status = 1 where resource_id in ?1", nativeQuery = true)
    void updateResourceInfoArchiveStatus(List<Long> resourceIds);
    //IPage<ResourceInfoEntity> selectResourceInfos(Page<?> page, @Param("query") ResourceInfoQueryDto queryDto);
}
