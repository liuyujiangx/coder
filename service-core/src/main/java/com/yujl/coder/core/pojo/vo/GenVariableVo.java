package com.yujl.coder.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "变量对象")
public class GenVariableVo {
    @ApiModelProperty(value = "变量名")
    private String variable;

    @ApiModelProperty(value = "变量描述")
    private String name;

    @ApiModelProperty(value = "所属模板ID")
    private Long templateId;

    @ApiModelProperty(value = "是否必填(1:非必填，0:必填)")
    private Boolean nullable;
}
