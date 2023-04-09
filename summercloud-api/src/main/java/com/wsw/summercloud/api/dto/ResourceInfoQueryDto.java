package com.wsw.summercloud.api.dto;

import com.wsw.summercloud.api.basic.PageParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 15:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "资源信息查询实体类")
public class ResourceInfoQueryDto extends PageParams implements Serializable {
    private static final long serialVersionUID = 714776675176751725L;

    @ApiModelProperty(value = "资源id")
    private Long resourceId;

    @ApiModelProperty(value = "是否有效 1:是 0:否")
    private Integer enableType;

    @ApiModelProperty(value = "归档状态 0:待处理 1:已处理")
    private Integer archiveStatus;

    @ApiModelProperty(value = "资源入库时间-开始")
    private Date createdTimeBegin;

    @ApiModelProperty(value = "资源入库时间-结束")
    private Date createdTimeEnd;

    @ApiModelProperty(value = "资源修改时间-开始")
    private Date updatedTimeBegin;

    @ApiModelProperty(value = "资源修改时间-结束")
    private Date updatedTimeEnd;
}
