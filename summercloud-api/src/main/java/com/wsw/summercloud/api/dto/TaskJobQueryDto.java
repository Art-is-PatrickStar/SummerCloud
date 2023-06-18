package com.wsw.summercloud.api.dto;

import com.wsw.summercloud.api.basic.PageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/8 22:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "TaskJobQueryDto", title = "任务信息查询实体类")
public class TaskJobQueryDto extends PageParams implements Serializable {
    @Serial
    private static final long serialVersionUID = -5190556210662506107L;

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

    @Schema(title = "任务创建时间-开始")
    private Date createdTimeBegin;

    @Schema(title = "任务创建时间-结束")
    private Date createdTimeEnd;

    @Schema(title = "任务修改时间-开始")
    private Date updatedTimeBegin;

    @Schema(title = "任务修改时间-结束")
    private Date updatedTimeEnd;
}
