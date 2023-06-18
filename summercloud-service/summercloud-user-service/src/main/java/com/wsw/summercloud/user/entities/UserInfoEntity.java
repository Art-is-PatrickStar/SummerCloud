package com.wsw.summercloud.user.entities;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "UserInfoEntity", title = "用户信息表实体类")
public class UserInfoEntity {
    @Schema(title = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
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
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @Schema(title = "用户信息修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
