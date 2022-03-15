package com.yujl.coder.core.service;

import com.yujl.coder.core.pojo.entity.GenCustemp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 自定义模板 服务类
 * </p>
 *
 * @author yujl
 * @since 2021-12-14
 */
public interface GenCustempService extends IService<GenCustemp> {

    GenCustemp getByFile(Long fileId);

    GenCustemp saveOne(GenCustemp genCustemp);
}
