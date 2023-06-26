package com.wsw.summercloud.common.utils;

import com.wsw.summercloud.api.basic.UserInfo;

/**
 * @Description: 用户信息工具类
 * @Author: wangsongwen
 * @Date: 2023/6/26 13:52
 */
public class UserInfoUtil {
    public static ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

    public static UserInfo getCurrentUserInfoThreadLocal() {
        return userInfoThreadLocal.get();
    }

    public static void putCurrentUserInfoThreadLocal(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    public static void putCurrentUserInfoThreadLocal(Long userId, String userName) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserName(userName);
        userInfoThreadLocal.set(userInfo);
    }

    public static void clearCurrentUserInfoThreadLocal() {
        userInfoThreadLocal.remove();
    }
}
