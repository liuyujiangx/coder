package com.yujl.coder.core.controller.api;


import com.yujl.coder.core.pojo.entity.GenVariable;
import com.yujl.coder.core.pojo.vo.GenVariableVo;
import com.yujl.coder.core.service.GenVariableService;
import com.yujl.common.exception.Assert;
import com.yujl.common.result.R;
import com.yujl.common.result.ResponseEnum;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yujl
 * @since 2021-12-09
 */
@RestController
@RequestMapping("/api/core/genVariable")
public class GenVariableController {
    @Resource
    private GenVariableService genVariableService;

    @GetMapping("/getByTempId/{tempId}")
    public R getByTempId(@PathVariable Long tempId) {
        return R.ok().data("list", genVariableService.getByTempId(tempId));
    }

    @PostMapping("/saveOne")
    public R saveOne(@RequestBody GenVariable genVariable){
        Assert.notNull(genVariable.getTemplateId(), ResponseEnum.FIELD_NULL_REEOR);
        genVariableService.save(genVariable);
        return R.ok().message("保存成功");
    }

    @PostMapping("/saveBatch")
    public R saveBatch(@RequestBody List<GenVariable> genVariables){
        genVariableService.saveBatch(genVariables);
        return R.ok().message("保存成功");
    }
}

