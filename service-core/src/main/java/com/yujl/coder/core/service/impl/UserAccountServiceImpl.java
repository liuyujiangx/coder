package com.yujl.coder.core.service.impl;

import com.yujl.coder.core.pojo.entity.UserAccount;
import com.yujl.coder.core.mapper.UserAccountMapper;
import com.yujl.coder.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author yujl
 * @since 2021-11-28
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
