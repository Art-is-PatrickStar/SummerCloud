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
 * @Date: 2023/4/8 22:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "任务信息查询实体类")
public class TaskJobQueryDto extends PageParams implements Serializable {
    private static final long serialVersionUID = -5190556210662506107L;

    @ApiModelProperty(value = "任务id")
    private Long jobId;

    @ApiModelProperty(value = "资源id")
    private Long resourceId;

    @ApiModelProperty(value = "任务状态 1:待处理 2:处理中 3:处理完成 4:不处理")
    private Integer jobStatus;

    @ApiModelProperty(value = "是否已上锁占用 0:否 1:是")
    private Integer isLock;

    @ApiModelProperty(value = "是否逻辑删除 0:否 1:是")
    private Integer isDelete;

    @ApiModelProperty(value = "任务创建时间-开始")
    private Date createdTimeBegin;

    @ApiModelProperty(value = "任务创建时间-结束")
    private Date createdTimeEnd;

    @ApiModelProperty(value = "任务修改时间-开始")
    private Date updatedTimeBegin;

    @ApiModelProperty(value = "任务修改时间-结束")
    private Date updatedTimeEnd;
}
