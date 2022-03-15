package com.yujl.coder.core.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuyj
 */
@Data
public class CustempVO {
    private Long id;

    private String fileName;

    private Long fileId;

    private String content;
}
