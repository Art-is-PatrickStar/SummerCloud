package com.wsw.summercloud.archive.repository;

import com.wsw.summercloud.archive.entities.ResourceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/17 17:35
 */
@Repository
public interface ResourceInfoRepository extends JpaRepository<ResourceInfoEntity, Long>, JpaSpecificationExecutor<ResourceInfoEntity> {
    @Modifying
    @Transactional
    @Query(value = "update ResourceInfoEntity set archiveStatus = 1 where resourceId in (:resourceIds)")
    void updateResourceInfoArchiveStatus(@Param("resourceIds") List<Long> resourceIds);
}
