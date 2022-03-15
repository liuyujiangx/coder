package com.yujl.coder.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.yujl.common.result.R;
import com.yujl.coder.core.listener.ExcelDictDTOListener;
import com.yujl.coder.core.pojo.dto.ExcelDictDTO;
import com.yujl.coder.core.pojo.entity.Dict;
import com.yujl.coder.core.mapper.DictMapper;
import com.yujl.coder.core.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author yujl
 * @since 2021-11-28
 */
@Service
@Slf4j
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private RedisTemplate redisTemplate;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public R importData(InputStream inputStream) {
        ExcelDictDTOListener excelDictListener = new ExcelDictDTOListener(baseMapper);
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, ExcelDictDTO.class, excelDictListener).sheet().doRead();
        log.info("importData finished");
        return R.ok().message("批量导入成功,共" + excelDictListener.getCount() + "条数据");
    }

    @Override
    public List<ExcelDictDTO> listDictData() {
        List<Dict> dicts = baseMapper.selectList(null);
        ArrayList<ExcelDictDTO> excelDictDtoS = new ArrayList<>(dicts.size());
        dicts.forEach(dict -> {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict, excelDictDTO);
            excelDictDtoS.add(excelDictDTO);
        });
        return excelDictDtoS;
    }

    @Override
    public List<Dict> listByParentId(Long parentId) {
        List<Dict> dicts = null;
        try {
            log.info("加载缓存：srb:core:dictList:{}",parentId);
            dicts = (List<Dict>) redisTemplate.opsForValue().get("srb:core:dictList:"+parentId);
            if (dicts!=null){
                log.info("加载缓存srb:core:dictList:{}成功,共{}条",parentId,dicts.size());
                return dicts;
            }
        }catch (Exception e){
            log.error("redis服务器异常：" + ExceptionUtils.getStackTrace(e));
        }

        log.info("缓存读取失败，正在从数据库中加载");


        dicts = baseMapper.selectList(new QueryWrapper<Dict>().eq("parent_Id", parentId));
        dicts.forEach(dict -> {
            dict.setHasChildren(hasChildren(dict.getId()));
        });

        //将数据存入redis
        try {
            redisTemplate.opsForValue().set("srb:core:dictList:" + parentId, dicts, 5, TimeUnit.MINUTES);
            log.info("数据存入redis");
        } catch (Exception e) {
            log.error("redis服务器异常：" + ExceptionUtils.getStackTrace(e));//此处不抛出异常，继续执行后面的代码
        }
        return dicts;
    }

    public boolean hasChildren(Long id) {
        Integer count = baseMapper.selectCount(new QueryWrapper<Dict>().eq("parent_id", id));
        return count > 0;
    }
}
