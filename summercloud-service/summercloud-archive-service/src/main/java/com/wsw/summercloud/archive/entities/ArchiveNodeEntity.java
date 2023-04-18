package com.wsw.summercloud.archive.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@ApiModel(value = "归档节点实体类")
public class ArchiveNodeEntity {
    @Id
    @ApiModelProperty(value = "归档id")
    private Long archiveId;

    @Column(name = "archive_rule")
    @ApiModelProperty(value = "归档规则")
    private String archiveRule;

    @Column(name = "enable")
    @ApiModelProperty(value = "是否启用 1:是 0:否")
    private Integer enable;

    @Column(name = "is_delete")
    @ApiModelProperty(value = "是否删除 1:是 0:否")
    private Integer isDelete;

    @Column(name = "created_time", insertable = false, updatable = false)
    @ApiModelProperty(value = "归档节点创建时间")
    private Date createdTime;

    @Column(name = "updated_time", insertable = false, updatable = false)
    @ApiModelProperty(value = "归档节点修改时间")
    private Date updatedTime;
}
