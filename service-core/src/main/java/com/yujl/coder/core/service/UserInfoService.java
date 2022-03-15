package com.yujl.coder.core.service;

import com.yujl.common.result.R;
import com.yujl.coder.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yujl.coder.core.pojo.vo.LoginVO;
import com.yujl.coder.core.pojo.vo.RegisterVO;
import com.yujl.coder.core.pojo.vo.UserInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author yujl
 * @since 2021-11-28
 */
public interface UserInfoService extends IService<UserInfo> {

    void register(RegisterVO registerVO);

    UserInfoVO login(LoginVO loginVO, String ip);

    R checkLogin(HttpServletRequest request);

    boolean checkMobile(String mobile);
}
