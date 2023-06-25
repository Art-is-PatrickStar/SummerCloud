package com.wsw.summercloud.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @Date: 2023/4/9 17:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "TaskJobRecordQueryDto", title = "任务信息记录查询实体类")
public class TaskJobRecordQueryDto extends PageParams implements Serializable {
    @Serial
    private static final long serialVersionUID = -6248780884789712789L;

    @Schema(title = "任务记录id")
    private Long recordId;

    @Schema(title = "任务id")
    private Long jobId;

    @Schema(title = "任务状态 1:待处理 2:处理中 3:处理完成 4:不处理")
    private Integer jobStatus;

    @Schema(title = "是否逻辑删除 0:否 1:是")
    private Integer isDelete;

    @Schema(title = "任务创建时间-开始")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTimeBegin;

    @Schema(title = "任务创建时间-结束")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTimeEnd;

    @Schema(title = "任务修改时间-开始")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTimeBegin;

    @Schema(title = "任务修改时间-结束")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTimeEnd;
}
