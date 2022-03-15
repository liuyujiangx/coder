package com.yujl.coder.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author 19145
 */
@Data
@ApiModel(description = "注册对象")
public class RegisterVO {
    private Integer userType;

    private String mobile;

    private String code;

    private String password;
}
