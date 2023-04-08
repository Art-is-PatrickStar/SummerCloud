package com.wsw.summercloud.archive.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "资源信息实体类")
public class ResourceInfoEntity {
    @ApiModelProperty(value = "资源id")
    @TableId(value = "resource_id", type = IdType.INPUT)
    private Long resourceId;

    @ApiModelProperty(value = "数据")
    private String data;

    @ApiModelProperty(value = "是否有效 1:是 0:否")
    private Integer enableType;

    @ApiModelProperty(value = "归档状态 0:待处理 1:已处理")
    private Integer archiveStatus;

    @ApiModelProperty(value = "资源入库时间")
    private Date createdTime;

    @ApiModelProperty(value = "资源修改时间")
    private Date updatedTime;
}
