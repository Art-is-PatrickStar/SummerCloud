package com.wsw.summercloud.archive.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "归档节点实体类")
public class ArchiveNodeEntity {
    @ApiModelProperty(value = "归档id")
    @TableId(value = "archive_id", type = IdType.INPUT)
    private Long archiveId;

    @ApiModelProperty(value = "归档队则")
    private String archiveRule;

    @ApiModelProperty(value = "是否启用 1:是 0:否")
    private Integer enable;

    @ApiModelProperty(value = "是否删除 1:是 0:否")
    private Integer isDelete;

    @ApiModelProperty(value = "归档节点创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "归档节点修改时间")
    private Date updatedTime;
}
