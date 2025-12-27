package com.mos.example.common.utils;

import cn.dev33.satoken.stp.StpUtil;
import com.mos.example.common.base.HttpStatusEnum;
import com.mos.example.common.exception.BusinessException;
import com.mos.example.common.vo.UserInfo;

/**
 * 用户信息工具类
 *
 * @author Han
 */
public class UserUtil {

    public static UserInfo getUserInfo() {
        if (StpUtil.isLogin()) {
            return (UserInfo) StpUtil.getTokenSession().get("user");
        }
        throw new BusinessException(HttpStatusEnum.UNAUTHORIZED);
    }
}
