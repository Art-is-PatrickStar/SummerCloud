package com.wsw.summercloud.api.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/8 22:06
 */
@Data
@ApiModel(value = "分页查询参数")
public class PageParams {
    @ApiModelProperty(value = "页码")
    private Integer currentPage;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;
}