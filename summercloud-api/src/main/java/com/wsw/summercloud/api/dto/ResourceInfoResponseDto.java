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
 * @Date: 2023/4/6 23:48
 */
@Data
@Schema(name = "ResourceInfoResponseDto", title = "资源信息响应实体类")
public class ResourceInfoResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5480792775192662847L;

    @Schema(title = "资源id")
    private Long resourceId;

    @Schema(title = "数据")
    private String data;

    @Schema(title = "是否有效 1:是 0:否")
    private Integer enableType;

    @Schema(title = "归档状态 0:待处理 1:已处理")
    private Integer archiveStatus;

    @Schema(title = "资源入库时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date createdTime;

    @Schema(title = "资源修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date updatedTime;
}
