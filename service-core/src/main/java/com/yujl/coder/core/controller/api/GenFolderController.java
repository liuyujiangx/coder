package com.yujl.coder.core.controller.api;


import com.yujl.coder.base.util.JwtUtils;
import com.yujl.coder.core.pojo.dto.FolderDTO;
import com.yujl.coder.core.pojo.entity.GenFolder;
import com.yujl.coder.core.pojo.vo.FolderVO;
import com.yujl.coder.core.service.GenFolderService;
import com.yujl.common.annotation.RequiredPermission;
import com.yujl.common.constants.PermissionConstants;
import com.yujl.common.result.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yujl
 * @since 2021-12-14
 */
@RestController
@RequestMapping("/api/core/genFolder")
@CrossOrigin
@RequiredPermission(PermissionConstants.LOGIN_AUTH)
public class GenFolderController {
    @Resource
    private GenFolderService genFolderService;

    @GetMapping("/tree/{tempId}")
    public R getTreeFolder(@PathVariable Long tempId) {
        FolderDTO folderDTO = genFolderService.getTreeFolder(tempId);
        return R.ok().data("treeFolder", folderDTO);
    }

    @GetMapping("/path/{id}")
    public R getFolderPath(@PathVariable Long id) {
        return R.ok().data("folderPath", genFolderService.getFolderPath(id));
    }

    @DeleteMapping("/del/{id}")
    public R delById(@PathVariable Long id) {
        genFolderService.delById(id);
        return R.ok();
    }

    @PostMapping("/save")
    public R save(@RequestBody FolderVO folderVO, HttpServletRequest request) {
        Long userId = JwtUtils.getUserId(request.getHeader("X-Token"));
        genFolderService.save(folderVO, userId);
        return R.ok();
    }

    @PostMapping("/update")
    public R update(@RequestBody FolderDTO folderDTO) {
        genFolderService.updateById(folderDTO);
        return R.ok();
    }
}

