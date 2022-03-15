package com.yujl.coder.core.service;

import com.yujl.coder.core.pojo.entity.GenVariable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yujl
 * @since 2021-12-09
 */
public interface GenVariableService extends IService<GenVariable> {

    List<GenVariable> getByTempId(Long tempId);
}
