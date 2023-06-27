package com.wsw.summercloud.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:51
 */
@Data
@Schema(name = "OpLogDto", title = "操作日志信息")
public class OpLogDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -4231911063865350513L;

    @Schema(title = "主键")
    private Long id;

    @Schema(title = "模块名")
    private String moduleType;

    @Schema(title = "模块id")
    private String moduleId;

    @Schema(title = "操作类型")
    private String operateType;

    @Schema(title = "操作内容")
    private String operateContent;

    @Schema(title = "创建人员")
    private String createdUser;

    @Schema(title = "创建时间")
    private Date createdTime;
}
