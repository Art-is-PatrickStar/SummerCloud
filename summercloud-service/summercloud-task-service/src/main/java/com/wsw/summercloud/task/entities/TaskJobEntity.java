package com.wsw.summercloud.task.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/7 15:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("task_job")
@ApiModel(value = "任务信息实体类")
public class TaskJobEntity {
    @ApiModelProperty(value = "任务id")
    @TableId(value = "job_id", type = IdType.INPUT)
    private Long jobId;

    @ApiModelProperty(value = "资源id")
    private Long resourceId;

    @ApiModelProperty(value = "任务状态 1:待处理 2:处理中 3:处理完成 4:不处理")
    private Integer jobStatus;

    @ApiModelProperty(value = "是否已上锁占用 0:否 1:是")
    private Integer isLock;

    @ApiModelProperty(value = "是否逻辑删除 0:否 1:是")
    private Integer isDelete;

    @ApiModelProperty(value = "任务创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "任务修改时间")
    private Date updatedTime;
}
