package com.wsw.summercloud.user.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/22 23:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert //insert对象时,生成动态的insert语句,如果这个字段的值是null就不会加入到insert语句中
@DynamicUpdate //update对象时,生成动态的update语句前,先查询该表在数据库中的字段值,并对比更新使用的对象中的字段值与数据库中的字段值是否相同,若相同(即该值没有修改),则该字段就不会被加入到update语句中
@Entity
@Table(name = "user_info")
@Schema(name = "UserInfoEntity", title = "用户信息表实体类")
public class UserInfoEntity {
    @Schema(title = "用户id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(title = "用户名")
    @Column(name = "username")
    private String username;

    @Schema(title = "密码")
    @Column(name = "password")
    private String password;

    @Schema(title = "邮箱")
    @Column(name = "email")
    private String email;

    @Schema(title = "是否逻辑删除 0:否 1:是")
    @Column(name = "is_delete")
    private Integer isDelete;

    @Schema(title = "用户信息创建时间")
    @Column(name = "created_time", insertable = false, updatable = false)
    private Date createdTime;

    @Schema(title = "用户信息修改时间")
    @Column(name = "updated_time", insertable = false, updatable = false)
    private Date updatedTime;
}