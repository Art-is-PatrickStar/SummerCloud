package com.wsw.summercloud.api.msg;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/6 22:47
 */
@Data
@Schema(name = "ResourceMsg", title = "资源信息")
public class ResourceMsg implements Serializable {
    @Serial
    private static final long serialVersionUID = 381039808565460515L;

    @Schema(title = "资源id")
    private Long resourceId;

    @Schema(title = "数据")
    private String data;

    @Schema(title = "是否有效 1:是 0:否")
    private Integer enableType;
}
