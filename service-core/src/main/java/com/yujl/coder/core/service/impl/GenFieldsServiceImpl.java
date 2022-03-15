package com.yujl.coder.core.service.impl;

import com.yujl.coder.core.pojo.entity.GenFields;
import com.yujl.coder.core.mapper.GenFieldsMapper;
import com.yujl.coder.core.service.GenFieldsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yujl
 * @since 2021-12-10
 */
@Service
public class GenFieldsServiceImpl extends ServiceImpl<GenFieldsMapper, GenFields> implements GenFieldsService {

    @Override
    public void saveBatch(List<GenFields> list) {
        for (GenFields fields : list) {
            if (fields.getId()>0L){
                if (baseMapper.selectById(fields.getId())!=null){
                    this.updateOne(fields);
                }else {
                    fields.setId(null);
                    this.saveOne(fields);
                }
            }else {
                this.saveOne(fields);
            }
        }
    }

    @Override
    public void saveOne(GenFields genFields) {
        baseMapper.insert(genFields);
    }

    @Override
    public void updateOne(GenFields genFields) {
        baseMapper.updateById(genFields);
    }
}
