package com.wsw.summercloud.task.entities;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "TaskJobEntity", title = "任务信息表实体类")
public class TaskJobEntity {
    @Schema(title = "任务id")
    @TableId(value = "job_id", type = IdType.INPUT)
    private Long jobId;

    @Schema(title = "资源id")
    private Long resourceId;

    @Schema(title = "归档id")
    private Long archiveId;

    @Schema(title = "任务状态 1:待处理 2:处理中 3:处理完成 4:不处理")
    private Integer jobStatus;

    @Schema(title = "是否已上锁占用 0:否 1:是")
    private Integer isLock;

    @Schema(title = "是否逻辑删除 0:否 1:是")
    private Integer isDelete;

    @Schema(title = "任务创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @Schema(title = "任务修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
