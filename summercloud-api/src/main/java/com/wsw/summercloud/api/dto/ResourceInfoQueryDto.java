package com.wsw.summercloud.api.dto;

import com.wsw.summercloud.api.basic.PageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 15:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ResourceInfoQueryDto", title = "资源信息查询实体类")
public class ResourceInfoQueryDto extends PageParams implements Serializable {
    @Serial
    private static final long serialVersionUID = 714776675176751725L;

    @Schema(title = "资源id")
    private Long resourceId;

    @Schema(title = "是否有效 1:是 0:否")
    private Integer enableType;

    @Schema(title = "归档状态 0:待处理 1:已处理")
    private Integer archiveStatus;

    @Schema(title = "资源入库时间-开始")
    private Date createdTimeBegin;

    @Schema(title = "资源入库时间-结束")
    private Date createdTimeEnd;

    @Schema(title = "资源修改时间-开始")
    private Date updatedTimeBegin;

    @Schema(title = "资源修改时间-结束")
    private Date updatedTimeEnd;
}
