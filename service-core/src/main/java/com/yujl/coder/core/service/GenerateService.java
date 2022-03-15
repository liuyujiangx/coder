package com.yujl.coder.core.service;

import com.yujl.coder.core.pojo.entity.FieldValue;

import java.util.List;

/**
 * @author liuyj
 */
public interface GenerateService {
    String generate(List<FieldValue> fieldValues, String template);
}
