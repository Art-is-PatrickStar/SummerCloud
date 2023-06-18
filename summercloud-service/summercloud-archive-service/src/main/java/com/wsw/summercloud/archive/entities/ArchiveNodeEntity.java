package com.wsw.summercloud.archive.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 16:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "archive_node")
@Schema(name = "ArchiveNodeEntity", title = "归档节点实体类")
public class ArchiveNodeEntity {
    @Id
    @Schema(title = "归档id")
    private Long archiveId;

    @Column(name = "archive_rule")
    @Schema(title = "归档规则")
    private String archiveRule;

    @Column(name = "enable")
    @Schema(title = "是否启用 1:是 0:否")
    private Integer enable;

    @Column(name = "is_delete")
    @Schema(title = "是否删除 1:是 0:否")
    private Integer isDelete;

    @Column(name = "created_time", insertable = false, updatable = false)
    @Schema(title = "归档节点创建时间")
    private Date createdTime;

    @Column(name = "updated_time", insertable = false, updatable = false)
    @Schema(title = "归档节点修改时间")
    private Date updatedTime;
}
