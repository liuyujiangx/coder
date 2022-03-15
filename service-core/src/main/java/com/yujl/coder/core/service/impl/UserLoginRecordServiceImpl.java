package com.yujl.coder.core.service.impl;

import com.yujl.coder.core.pojo.entity.UserLoginRecord;
import com.yujl.coder.core.mapper.UserLoginRecordMapper;
import com.yujl.coder.core.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author yujl
 * @since 2021-11-28
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

}
