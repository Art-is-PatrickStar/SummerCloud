package com.wsw.summercloud.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/27 23:38
 */
@Data
@Schema(name = "ApiLogDto", title = "api日志信息")
public class ApiLogDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -7744378352293298735L;

    @Schema(title = "主键")
    private Long id;

    @Schema(title = "请求方法")
    private String method;

    @Schema(title = "请求用户")
    private String operateUser;

    @Schema(title = "请求参数")
    private String params;

    @Schema(title = "请求结果")
    private String result;

    @Schema(title = "请求耗时")
    private Long costTime;

    @Schema(title = "请求异常信息")
    private String exception;
}
