package com.yujl.coder.core.controller.api;


import com.yujl.common.exception.Assert;
import com.yujl.common.result.R;
import com.yujl.common.result.ResponseEnum;
import com.yujl.common.util.RegexValidateUtils;
import com.yujl.coder.core.pojo.vo.LoginVO;
import com.yujl.coder.core.pojo.vo.RegisterVO;
import com.yujl.coder.core.pojo.vo.UserInfoVO;
import com.yujl.coder.core.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.beans.Transient;

/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author yujl
 * @since 2021-11-28
 */
@Api(tags = "会员接口")
@RestController
@RequestMapping("/api/core/userInfo")
@Slf4j
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/register")
    public R register(@RequestBody RegisterVO registerVO) {
        String mobile = registerVO.getMobile();

        String password = registerVO.getPassword();

        String code = registerVO.getCode();

        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);

        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);

        Assert.notEmpty(code, ResponseEnum.CODE_NULL_ERROR);

        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);

        String relCode = (String) redisTemplate.opsForValue().get("srb:sms:code:" + mobile);

        Assert.equals(code, relCode, ResponseEnum.CODE_ERROR);
        redisTemplate.delete("srb:sms:code:" + mobile);
        userInfoService.register(registerVO);
        return R.ok().message("注册成功");
    }

    @PostMapping("/login")
    public R login(@RequestBody LoginVO loginVO, HttpServletRequest request) {
        String mobile = loginVO.getMobile();

        String password = loginVO.getPassword();

        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);

        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);

        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);

        String ip = request.getRemoteAddr();

        UserInfoVO userInfoVO = userInfoService.login(loginVO, ip);

        return R.ok().data("userInfo", userInfoVO);
    }

    @ApiOperation("校验令牌")
    @GetMapping("/checkToken")
    public R checkToken(HttpServletRequest request) {
        return userInfoService.checkLogin(request);
    }

    @ApiOperation("校验手机号是否注册")
    @GetMapping("/checkMobile/{mobile}")
    public boolean checkMobile(@PathVariable String mobile){
        return userInfoService.checkMobile(mobile);
    }
}

