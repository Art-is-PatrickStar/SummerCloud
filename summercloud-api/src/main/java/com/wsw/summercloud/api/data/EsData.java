package com.wsw.summercloud.api.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/4 22:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsData {
    private String dataId;

    private String data;

    private String createdTime;

    private String updatedTime;
}
