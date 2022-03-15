package com.yujl.coder.core.service;

import com.yujl.common.result.R;
import com.yujl.coder.core.pojo.dto.ExcelDictDTO;
import com.yujl.coder.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author yujl
 * @since 2021-11-28
 */
public interface DictService extends IService<Dict> {
    R importData(InputStream inputStream);

    List<ExcelDictDTO> listDictData();

    List<Dict> listByParentId(Long parentId);
}
