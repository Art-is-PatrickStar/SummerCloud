package com.wsw.summercloud.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/17 21:55
 */
@Data
@Schema(name = "ArchiveNodeRequestDto", title = "归档节点请求实体类")
public class ArchiveNodeRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 3696229950120156388L;

    @Schema(title = "归档id")
    private Long archiveId;

    @Schema(title = "归档规则")
    private String archiveRule;

    @Schema(title = "是否启用 1:是 0:否")
    private Integer enable;

    @Schema(title = "是否删除 1:是 0:否")
    private Integer isDelete;

    @Schema(title = "归档节点创建时间")
    private Date createdTime;

    @Schema(title = "归档节点修改时间")
    private Date updatedTime;
}
