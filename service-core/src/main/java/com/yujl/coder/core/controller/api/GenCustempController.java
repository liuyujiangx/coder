package com.yujl.coder.core.controller.api;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yujl.coder.base.util.JwtUtils;
import com.yujl.coder.core.pojo.entity.GenCustemp;
import com.yujl.coder.core.pojo.vo.CustempVO;
import com.yujl.coder.core.service.GenCustempService;
import com.yujl.coder.core.service.GenFolderService;
import com.yujl.common.annotation.RequiredPermission;
import com.yujl.common.constants.PermissionConstants;
import com.yujl.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 自定义模板 前端控制器
 * </p>
 *
 * @author yujl
 * @since 2021-12-14
 */
@RestController
@RequestMapping("/api/core/genCustemp")
@CrossOrigin
@Slf4j
@RequiredPermission(PermissionConstants.LOGIN_AUTH)
public class GenCustempController {
    @Resource
    private GenCustempService genCustempService;

    @Resource
    private GenFolderService genFolderService;

    @GetMapping("/getByFile/{fileId}")
    public R getByFile(@PathVariable Long fileId, HttpServletRequest request) {
        Map<String,String> tempContent = new HashMap<>();
        GenCustemp genCustemp = genCustempService.getByFile(fileId);
        if (genCustemp != null) {
            tempContent.put("id",genCustemp.getId().toString());
            tempContent.put("content",genCustemp.getContent());
            return R.ok().data("tempContent", tempContent);
        }
        genCustemp = new GenCustemp();
        genCustemp.setFileId(fileId);
        genCustemp.setFileName(genFolderService.getById(fileId).getName());
        genCustemp.setUserId(JwtUtils.getUserId(request.getHeader("X-Token")));
        genCustemp.setCreateTime(LocalDateTime.now());
        genCustemp.setUpdateTime(LocalDateTime.now());
        genCustemp.setDeleted(false);
        genCustemp = genCustempService.saveOne(genCustemp);
        tempContent.put("id",genCustemp.getId().toString());
        tempContent.put("content",genCustemp.getContent());
        return R.ok().data("tempContent", tempContent);
    }

    @PostMapping("/update")
    public R update(@RequestBody CustempVO custempVO){
        GenCustemp genCustemp = new GenCustemp();
        genCustemp.setId(custempVO.getId());
        genCustemp.setContent(custempVO.getContent());
        genCustemp.setFileId(custempVO.getFileId());
        genCustemp.setFileName(custempVO.getFileName());
        genCustempService.updateById(genCustemp);
        return R.ok();
    }
}

