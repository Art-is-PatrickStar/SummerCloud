package com.wsw.summercloud.task.entities;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 15:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("task_job_record")
@ApiModel(value = "任务信息记录表实体类")
public class TaskJobRecordEntity {
    @ApiModelProperty(value = "任务记录id")
    @TableId(value = "record_id", type = IdType.INPUT)
    private Long recordId;

    @ApiModelProperty(value = "任务id")
    private Long jobId;

    @ApiModelProperty(value = "任务状态 1:待处理 2:处理中 3:处理完成 4:不处理")
    private Integer jobStatus;

    @ApiModelProperty(value = "是否逻辑删除 0:否 1:是")
    private Integer isDelete;

    @ApiModelProperty(value = "任务记录创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty(value = "任务记录修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
