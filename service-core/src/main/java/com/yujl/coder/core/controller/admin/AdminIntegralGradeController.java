package com.yujl.coder.core.controller.admin;

import com.yujl.common.annotation.RequiredPermission;
import com.yujl.common.constants.PermissionConstants;
import com.yujl.common.exception.BusinessException;
import com.yujl.common.result.R;
import com.yujl.common.result.ResponseEnum;
import com.yujl.coder.core.pojo.entity.IntegralGrade;
import com.yujl.coder.core.service.IntegralGradeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("admin/core/integralGrade")
@Slf4j
@CrossOrigin
public class AdminIntegralGradeController {
    @Resource
    private IntegralGradeService integralGradeService;

    @RequiredPermission(PermissionConstants.LOGIN_AUTH)
    @GetMapping("/list")
    public R listAll() {
        return R.ok().data("list", integralGradeService.list());
    }

    @RequiredPermission(PermissionConstants.LOGIN_AUTH)
    @GetMapping("get/{id}")
    public R getById(@PathVariable Long id) {
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if (integralGrade == null) {
            throw new BusinessException(ResponseEnum.DATA_NOT_NULL_ERROR);
        }
        return R.ok().data("record", integralGradeService.getById(id));
    }

    @RequiredPermission(PermissionConstants.LOGIN_AUTH)
    @DeleteMapping("/remove/{id}")
    public R removeById(@PathVariable Long id) {
        boolean res = integralGradeService.removeById(id);
        if (res) {
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    @RequiredPermission(PermissionConstants.LOGIN_AUTH)
    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade) {

        //如果借款额度为空就手动抛出一个自定义的异常！
        if (integralGrade.getBorrowAmount() == null) {
            //BORROW_AMOUNT_NULL_ERROR(-201, "借款额度不能为空"),
            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        }

        boolean result = integralGradeService.save(integralGrade);
        if (result) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("保存失败");
        }
    }

    @RequiredPermission(PermissionConstants.LOGIN_AUTH)
    @PutMapping("/update")
    public R update(@RequestBody IntegralGrade integralGrade) {
        if (getById(integralGrade.getId()).getData().get("record") == null) {
            throw new BusinessException(ResponseEnum.DATA_NOT_NULL_ERROR);
        }
        integralGradeService.updateById(integralGrade);
        return R.ok().message("成功");
    }
}
