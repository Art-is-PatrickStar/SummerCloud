package com.wsw.summercloud.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/19 16:12
 */
@Data
@Schema(name = "ResourceInfoRequestDto", title = "资源信息请求实体类")
public class ResourceInfoRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -8858818581471931491L;

    @Schema(title = "资源id")
    private Long resourceId;

    @Schema(title = "数据")
    private String data;

    @Schema(title = "是否有效 1:是 0:否")
    private Integer enableType;
}
