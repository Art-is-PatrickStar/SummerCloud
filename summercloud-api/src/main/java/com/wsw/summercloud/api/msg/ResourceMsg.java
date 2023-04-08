package com.wsw.summercloud.api.msg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/6 22:47
 */
@Data
@ApiModel(value = "资源信息")
public class ResourceMsg implements Serializable {
    private static final long serialVersionUID = 381039808565460515L;

    @ApiModelProperty(value = "资源id")
    private Long resourceId;

    @ApiModelProperty(value = "数据")
    private String data;

    @ApiModelProperty(value = "是否有效 1:是 0:否")
    private Integer enableType;
}
