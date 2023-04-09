package com.wsw.summercloud.common.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 16:51
 */
@Data
@ApiModel("日志信息")
public class OpLogDTO implements Serializable {
    private static final long serialVersionUID = -4231911063865350513L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "模块名")
    private String moduleType;

    @ApiModelProperty(value = "模块id")
    private String moduleId;

    @ApiModelProperty(value = "操作类型")
    private String operateType;

    @ApiModelProperty(value = "操作内容")
    private String operateContent;

    @ApiModelProperty(value = "创建人员")
    private String createdUser;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
}
