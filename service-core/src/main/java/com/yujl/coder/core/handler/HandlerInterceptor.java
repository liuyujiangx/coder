package com.yujl.coder.core.handler;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yujl.common.annotation.RequiredPermission;
import com.yujl.common.exception.BusinessException;
import com.yujl.common.result.R;
import com.yujl.common.result.ResponseEnum;
import com.yujl.coder.core.service.UserInfoService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 19145
 */
public class HandlerInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserInfoService userInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!isPermission(request, handler)) {
            throw new BusinessException(ResponseEnum.LOGIN_AUTH_ERROR);
        }
        return true;
    }

    private boolean isPermission(HttpServletRequest request, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
            // 如果方法上的注解为空 则获取类的注解
            if (requiredPermission == null) {
                requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);
            }
            // 如果标记了注解，则判断权限
            if (requiredPermission != null && StringUtils.isNotBlank(requiredPermission.value())) {
                // redis或数据库 中获取该用户的权限信息 并判断是否有权限
                Integer isLogin = userInfoService.checkLogin(request).getCode();
                return 0 == isLogin;
            }
        }
        return true;
    }
}
