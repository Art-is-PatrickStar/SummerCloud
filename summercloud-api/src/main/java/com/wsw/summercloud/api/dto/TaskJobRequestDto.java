package com.wsw.summercloud.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/7 16:05
 */
@Data
@ApiModel(value = "任务信息请求实体类")
public class TaskJobRequestDto implements Serializable {
    private static final long serialVersionUID = -6881523446281128275L;

    @ApiModelProperty(value = "任务id")
    private Long jobId;

    @ApiModelProperty(value = "资源id")
    private Long resourceId;

    @ApiModelProperty(value = "归档id")
    private Long archiveId;

    @ApiModelProperty(value = "任务状态 1:待处理 2:处理中 3:处理完成 4:不处理")
    private Integer jobStatus;

    @ApiModelProperty(value = "是否已上锁占用 0:否 1:是")
    private Integer isLock;

    @ApiModelProperty(value = "是否逻辑删除 0:否 1:是")
    private Integer isDelete;
}
