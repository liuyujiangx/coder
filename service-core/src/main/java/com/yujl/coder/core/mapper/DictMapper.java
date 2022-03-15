package com.yujl.coder.core.mapper;

import com.yujl.coder.core.pojo.dto.ExcelDictDTO;
import com.yujl.coder.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author yujl
 * @since 2021-11-28
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(List<ExcelDictDTO> list);
}
