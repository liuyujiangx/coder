package com.yujl.coder.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yujl.coder.core.pojo.entity.GenCustemp;
import com.yujl.coder.core.mapper.GenCustempMapper;
import com.yujl.coder.core.service.GenCustempService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 自定义模板 服务实现类
 * </p>
 *
 * @author yujl
 * @since 2021-12-14
 */
@Service
public class GenCustempServiceImpl extends ServiceImpl<GenCustempMapper, GenCustemp> implements GenCustempService {

    @Override
    public GenCustemp getByFile(Long fileId) {
        QueryWrapper<GenCustemp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_id",fileId);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public GenCustemp saveOne(GenCustemp genCustemp) {
        genCustemp.setContent(getDefaultContent(genCustemp.getFileName()));
        baseMapper.insert(genCustemp);
        genCustemp = this.getByFile(genCustemp.getFileId());
        return genCustemp;
    }

    public String getDefaultContent(String fileName){
        StringBuffer sb = new StringBuffer();
        sb.append("public class ").append(fileName).append(" {").append("\n").append("}");
        return sb.toString();
    }
}
