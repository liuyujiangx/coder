package com.yujl.coder.core.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.yujl.common.exception.BusinessException;
import com.yujl.common.result.R;
import com.yujl.common.result.ResponseEnum;
import com.yujl.coder.core.pojo.dto.ExcelDictDTO;
import com.yujl.coder.core.pojo.entity.Dict;
import com.yujl.coder.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author liuyj
 */
@Api(tags = "数据字典管理")
@RestController
@RequestMapping("admin/core/dict")
@Slf4j
@CrossOrigin
public class AdminDictController {

    @Autowired
    private DictService dictService;

    @ApiOperation("Excel批量导入数据字典")
    @PostMapping("/import")
    public R batchImport(@ApiParam(value = "Excel文件", required = true)
                         @RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            return dictService.importData(inputStream);
        } catch (Exception e) {
            log.error(e.getMessage() + e);
            throw new BusinessException(ResponseEnum.BAD_SQL_GRAMMAR_ERROR);
        }
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("mydict", "utf-8").replace("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet("数据字典").doWrite(dictService.listDictData());

        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ResponseEnum.EXPORT_DATA_ERROR);
        }
    }

    @ApiOperation("根据上级id获取子节点数据列表")
    @GetMapping("/listByParentId/{parentId}")
    public R listByParentId(
            @ApiParam(value = "上级节点id", required = true)
            @PathVariable Long parentId) {
        List<Dict> dictList = dictService.listByParentId(parentId);
        return R.ok().data("list", dictList);
    }


}
