package com.wsw.summercloud.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/19 16:12
 */
@Data
@ApiModel(value = "资源信息请求实体类")
public class ResourceInfoRequestDto implements Serializable {
    private static final long serialVersionUID = -8858818581471931491L;

    @ApiModelProperty(value = "资源id")
    private Long resourceId;

    @ApiModelProperty(value = "数据")
    private String data;

    @ApiModelProperty(value = "是否有效 1:是 0:否")
    private Integer enableType;
}
