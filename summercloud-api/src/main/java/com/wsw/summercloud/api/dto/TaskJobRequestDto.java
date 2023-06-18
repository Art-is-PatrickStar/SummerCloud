package com.wsw.summercloud.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/7 16:05
 */
@Data
@Schema(name = "TaskJobRequestDto", title = "任务信息请求实体类")
public class TaskJobRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -6881523446281128275L;

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
}
