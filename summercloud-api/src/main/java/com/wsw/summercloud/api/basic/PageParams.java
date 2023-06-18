package com.wsw.summercloud.api.basic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/8 22:06
 */
@Data
@Schema(name = "PageParams", title = "分页查询参数")
public class PageParams {
    @Schema(title = "页码")
    private Integer currentPage;

    @Schema(title = "每页数量")
    private Integer pageSize;
}