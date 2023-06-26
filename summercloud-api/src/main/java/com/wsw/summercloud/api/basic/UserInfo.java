package com.wsw.summercloud.api.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户信息
 * @Author: wangsongwen
 * @Date: 2023/6/26 13:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long UserId;
    private String UserName;
}
