package com.wsw.summercloud.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/28 10:45
 */
@Data
@Schema(name = "UserLoginResponseDto", title = "用户登录响应参数")
public class UserLoginResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 7704655353203014994L;

    @Schema(title = "用户id")
    private Long id;

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "token")
    private String token;
}
