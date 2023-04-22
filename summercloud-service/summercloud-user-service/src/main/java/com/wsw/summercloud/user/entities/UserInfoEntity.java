package com.wsw.summercloud.user.entities;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/22 23:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
@ApiModel(value = "用户信息表实体类")
public class UserInfoEntity {
    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "是否逻辑删除 0:否 1:是")
    private Integer isDelete;

    @ApiModelProperty(value = "用户信息创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty(value = "用户信息修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
