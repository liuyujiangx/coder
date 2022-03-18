package com.yujl.coder.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yujl.common.exception.Assert;
import com.yujl.common.result.R;
import com.yujl.common.result.ResponseEnum;
import com.yujl.common.util.MD5;
import com.yujl.coder.base.util.JwtUtils;
import com.yujl.coder.core.mapper.UserLoginRecordMapper;
import com.yujl.coder.core.pojo.entity.UserInfo;
import com.yujl.coder.core.mapper.UserInfoMapper;
import com.yujl.coder.core.pojo.entity.UserLoginRecord;
import com.yujl.coder.core.pojo.vo.LoginVO;
import com.yujl.coder.core.pojo.vo.RegisterVO;
import com.yujl.coder.core.pojo.vo.UserInfoVO;
import com.yujl.coder.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author yujl
 * @since 2021-11-28
 */
@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Resource
    private UserLoginRecordMapper userLoginRecordMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void register(RegisterVO registerVO) {
        //判断用户是否被注册
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", registerVO.getMobile());
        Integer count = baseMapper.selectCount(queryWrapper);
        Assert.isTrue(0 == count, ResponseEnum.MOBILE_EXIST_ERROR);

        //插入用户基本信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserType(registerVO.getUserType());
        userInfo.setNickName(registerVO.getMobile());
        userInfo.setName(registerVO.getMobile());
        userInfo.setMobile(registerVO.getMobile());
        userInfo.setPassword(MD5.encrypt(registerVO.getPassword()));
        userInfo.setStatus(UserInfo.STATUS_NORMAL); //正常
        //设置一张静态资源服务器上的头像图片
        userInfo.setHeadImg("https://srb-file.oss-cn-beijing.aliyuncs.com/avatar/07.jpg");
        baseMapper.insert(userInfo);
    }

    @Override
    public UserInfoVO login(LoginVO loginVO, String ip) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        Integer userType = loginVO.getUserType();

        //获取会员
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        queryWrapper.eq("user_type", userType);

        UserInfo userInfo = baseMapper.selectOne(queryWrapper);
        Assert.notNull(userInfo, ResponseEnum.LOGIN_MOBILE_ERROR);
        Assert.equals(MD5.encrypt(password), userInfo.getPassword(), ResponseEnum.LOGIN_PASSWORD_ERROR);
        Assert.equals(userInfo.getStatus(), UserInfo.STATUS_NORMAL, ResponseEnum.LOGIN_DISABLED_ERROR);

        UserLoginRecord userLoginRecord = new UserLoginRecord();
        userLoginRecord.setUserId(userInfo.getId());
        userLoginRecord.setIp(ip);
        userLoginRecordMapper.insert(userLoginRecord);

        //生成token
        String token = JwtUtils.createToken(userInfo.getId(), userInfo.getName());
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setToken(token);
        userInfoVO.setName(userInfo.getName());
        userInfoVO.setNickName(userInfo.getNickName());
        userInfoVO.setHeadImg(userInfo.getHeadImg());
        userInfoVO.setMobile(userInfo.getMobile());
        userInfoVO.setUserType(userType);

        return userInfoVO;
    }

    @Override
    public R checkLogin(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        boolean result = JwtUtils.checkToken(token);

        if(result){
            return R.ok();
        }else{
            return R.setResult(ResponseEnum.LOGIN_AUTH_ERROR);
        }
    }

    @Override
    public boolean checkMobile(String mobile) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }
}
