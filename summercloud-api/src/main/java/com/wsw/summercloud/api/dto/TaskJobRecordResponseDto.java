package com.wsw.summercloud.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 17:10
 */
@Data
@ApiModel(value = "任务信息记录响应实体类")
public class TaskJobRecordResponseDto implements Serializable {
    private static final long serialVersionUID = -8456572514580295818L;
    @ApiModelProperty(value = "任务记录id")
    private Long recordId;

    @ApiModelProperty(value = "任务id")
    private Long jobId;

    @ApiModelProperty(value = "资源id")
    private Long resourceId;

    @ApiModelProperty(value = "任务状态 1:待处理 2:处理中 3:处理完成 4:不处理")
    private Integer jobStatus;

    @ApiModelProperty(value = "是否逻辑删除 0:否 1:是")
    private Integer isDelete;

    @ApiModelProperty(value = "任务记录创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "任务记录修改时间")
    private Date updatedTime;
}
