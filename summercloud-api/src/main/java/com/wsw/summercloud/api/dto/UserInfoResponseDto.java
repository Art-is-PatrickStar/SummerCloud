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
 * @Date: 2023/4/23 0:11
 */
@Data
@Schema(name = "UserInfoResponseDto", title = "用户信息响应实体类")
public class UserInfoResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 2186107229113172504L;

    @Schema(title = "用户id")
    private Long id;

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "密码")
    private String password;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "是否逻辑删除 0:否 1:是")
    private Integer isDelete;

    @Schema(title = "用户信息创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date createdTime;

    @Schema(title = "用户信息修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date updatedTime;
}
