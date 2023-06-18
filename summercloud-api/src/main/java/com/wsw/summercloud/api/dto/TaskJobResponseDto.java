package com.wsw.summercloud.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/8 22:39
 */
@Data
@Schema(name = "TaskJobResponseDto", title = "任务信息响应实体类")
public class TaskJobResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -4282339139406850013L;

    @Schema(title = "任务id")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date createdTime;

    @Schema(title = "任务修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date updatedTime;
}
