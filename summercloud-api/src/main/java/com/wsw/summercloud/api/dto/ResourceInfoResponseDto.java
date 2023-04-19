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
 * @Date: 2023/4/6 23:48
 */
@Data
@ApiModel(value = "资源信息响应实体类")
public class ResourceInfoResponseDto implements Serializable {
    private static final long serialVersionUID = -5480792775192662847L;

    @ApiModelProperty(value = "资源id")
    private Long resourceId;

    @ApiModelProperty(value = "数据")
    private String data;

    @ApiModelProperty(value = "是否有效 1:是 0:否")
    private Integer enableType;

    @ApiModelProperty(value = "归档状态 0:待处理 1:已处理")
    private Integer archiveStatus;

    @ApiModelProperty(value = "资源入库时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "资源修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date updatedTime;
}
