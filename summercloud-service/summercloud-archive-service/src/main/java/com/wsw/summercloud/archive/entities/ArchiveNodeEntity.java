package com.wsw.summercloud.archive.entities;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 16:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("archive_node")
@Schema(name = "ArchiveNodeEntity", title = "归档节点实体类")
public class ArchiveNodeEntity {
    @Schema(title = "归档id")
    @TableId(value = "archive_id", type = IdType.INPUT)
    private Long archiveId;

    @Schema(title = "归档规则")
    private String archiveRule;

    @Schema(title = "是否启用 1:是 0:否")
    private Integer enable;

    @Schema(title = "是否删除 1:是 0:否")
    private Integer isDelete;

    @Schema(title = "归档节点创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @Schema(title = "归档节点修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
