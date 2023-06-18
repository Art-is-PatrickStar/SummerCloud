package com.wsw.summercloud.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/28 10:42
 */
@Data
@Schema(name = "UserLoginRequestDto", title = "用户登录请求参数")
public class UserLoginRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 4280441820382266480L;

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 1, max = 200)
    private String password;

    @Schema(title = "邮箱")
    private String email;
}
