package com.wsw.summercloud.archive.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/5 22:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert //insert对象时,生成动态的insert语句,如果这个字段的值是null就不会加入到insert语句中
@DynamicUpdate //update对象时,生成动态的update语句前,先查询该表在数据库中的字段值,并对比更新使用的对象中的字段值与数据库中的字段值是否相同,若相同(即该值没有修改),则该字段就不会被加入到update语句中
@Entity
@Table(name = "resource_info")
@ApiModel(value = "资源信息实体类")
public class ResourceInfoEntity {
    @Id
    @ApiModelProperty(value = "资源id")
    private Long resourceId;

    @ApiModelProperty(value = "数据")
    @Column(name = "data")
    private String data;

    @ApiModelProperty(value = "是否有效 1:是 0:否")
    @Column(name = "enable_type")
    private Integer enableType;

    @ApiModelProperty(value = "归档状态 0:待处理 1:已处理")
    @Column(name = "archive_status")
    private Integer archiveStatus;

    @ApiModelProperty(value = "资源入库时间")
    @Column(name = "created_time", insertable = false, updatable = false)
    private Date createdTime;

    @ApiModelProperty(value = "资源修改时间")
    @Column(name = "updated_time", insertable = false, updatable = false)
    private Date updatedTime;
}
