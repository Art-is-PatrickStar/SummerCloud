package com.wsw.summercloud.api.dto;

import com.wsw.summercloud.api.basic.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/11 15:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EsQueryDto extends PageParams implements Serializable {
    @Serial
    private static final long serialVersionUID = 441010717716040712L;

    private String indexName;

    private String documentId;

    private String queryField;

    private String queryValue;

    private String sortField;
}
