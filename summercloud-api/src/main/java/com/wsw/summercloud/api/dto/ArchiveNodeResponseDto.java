package com.wsw.summercloud.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 17:01
 */
@Data
@ApiModel(value = "归档节点响应实体类")
public class ArchiveNodeResponseDto implements Serializable {
    private static final long serialVersionUID = 7866202147737800820L;

    @ApiModelProperty(value = "归档id")
    private Long archiveId;

    @ApiModelProperty(value = "归档队则")
    private String archiveRule;

    @ApiModelProperty(value = "是否启用 1:是 0:否")
    private Integer enable;

    @ApiModelProperty(value = "是否删除 1:是 0:否")
    private Integer isDelete;

    @ApiModelProperty(value = "归档节点创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "归档节点修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date updatedTime;
}
