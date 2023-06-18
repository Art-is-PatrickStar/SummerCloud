package com.wsw.summercloud.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/16 17:01
 */
@Data
@Schema(name = "ArchiveNodeResponseDto", title = "归档节点响应实体类")
public class ArchiveNodeResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 7866202147737800820L;

    @Schema(title = "归档id")
    private Long archiveId;

    @Schema(title = "归档规则")
    private String archiveRule;

    @Schema(title = "是否启用 1:是 0:否")
    private Integer enable;

    @Schema(title = "是否删除 1:是 0:否")
    private Integer isDelete;

    @Schema(title = "归档节点创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date createdTime;

    @Schema(title = "归档节点修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date updatedTime;
}
