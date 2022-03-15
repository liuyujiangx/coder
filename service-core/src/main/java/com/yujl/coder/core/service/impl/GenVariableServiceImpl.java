package com.yujl.coder.core.service.impl;

import com.yujl.coder.core.pojo.entity.GenVariable;
import com.yujl.coder.core.mapper.GenVariableMapper;
import com.yujl.coder.core.service.GenVariableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yujl
 * @since 2021-12-09
 */
@Service
public class GenVariableServiceImpl extends ServiceImpl<GenVariableMapper, GenVariable> implements GenVariableService {

    @Resource
    private GenVariableMapper genVariableMapper;

    @Override
    public List<GenVariable> getByTempId(Long tempId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("template_id", tempId);

        return genVariableMapper.selectByMap(columnMap);
    }
}
