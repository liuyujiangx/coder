package com.yujl.coder.core.controller.api;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yujl.coder.core.pojo.entity.GenCustemp;
import com.yujl.coder.core.pojo.entity.GenFields;
import com.yujl.coder.core.service.GenFieldsService;
import com.yujl.common.annotation.RequiredPermission;
import com.yujl.common.constants.PermissionConstants;
import com.yujl.common.result.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yujl
 * @since 2021-12-10
 */
@RestController
@RequestMapping("/api/core/genFields")
@RequiredPermission(PermissionConstants.LOGIN_AUTH)
public class GenFieldsController {
    @Resource
    private GenFieldsService genFieldsService;

    @PostMapping("/save")
    public R saveBatch(@RequestBody List<GenFields> fields) {
        genFieldsService.saveBatch(fields);
        return R.ok("保存成功");
    }

    @GetMapping("/getByTempId/{tempId}")
    public R getByTempId(@PathVariable Long tempId) {
        QueryWrapper<GenFields> queryWrapper = new QueryWrapper<>();
        List<GenFields> list = genFieldsService.list(queryWrapper);
        return R.ok().data("fieldList", list);
    }

    @PostMapping("/del")
    public R del(@RequestBody GenFields genFields) {
        return R.ok().data("delete", genFieldsService.removeById(genFields.getId()));
    }
}

