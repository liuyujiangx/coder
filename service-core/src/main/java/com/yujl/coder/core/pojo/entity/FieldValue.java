package com.yujl.coder.core.pojo.entity;

import lombok.Data;

/**
 * @author liuyj
 */
@Data
public class FieldValue {
    private Long fieldId;

    private String fieldName;

    private Object value;
}
