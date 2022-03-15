package com.yujl.coder.core.service;

import com.yujl.coder.core.pojo.entity.GenFields;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yujl
 * @since 2021-12-10
 */
public interface GenFieldsService extends IService<GenFields> {
    void saveBatch(List<GenFields> list);

    void saveOne(GenFields genFields);

    void updateOne(GenFields genFields);
}
