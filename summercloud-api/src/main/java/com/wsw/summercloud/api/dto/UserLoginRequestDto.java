package com.wsw.summercloud.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/28 10:42
 */
@Data
@ApiModel("用户登录请求参数")
public class UserLoginRequestDto implements Serializable {
    private static final long serialVersionUID = 4280441820382266480L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 1, max = 200)
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;
}
