package com.wsw.summercloud.archive.repository;

import com.wsw.summercloud.archive.entities.ArchiveNodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/17 17:32
 */
@Repository
public interface ArchiveNodeRepository extends JpaRepository<ArchiveNodeEntity, Long>, JpaSpecificationExecutor<ArchiveNodeEntity> {
    @Query("from ArchiveNodeEntity where isDelete = 0") //jpql
    //@Query(value = "select * from archive_node where is_delete = 0", nativeQuery = true)  //sql
    List<ArchiveNodeEntity> getAllArchiveNodes();
}
