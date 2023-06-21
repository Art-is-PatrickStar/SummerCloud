package com.wsw.summercloud.archive.entities;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/5 22:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("resource_info")
@Schema(name = "ResourceInfoEntity", title = "资源信息实体类")
public class ResourceInfoEntity {
    @Schema(title = "资源id")
    @TableId(value = "resource_id", type = IdType.INPUT)
    private Long resourceId;

    @Schema(title = "数据")
    private String data;

    @Schema(title = "是否有效 1:是 0:否")
    private Integer enableType;

    @Schema(title = "归档状态 0:待处理 1:已处理")
    private Integer archiveStatus;

    @Schema(title = "资源入库时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @Schema(title = "资源修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}
