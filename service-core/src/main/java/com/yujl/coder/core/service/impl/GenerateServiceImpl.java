package com.yujl.coder.core.service.impl;

import com.yujl.coder.core.pojo.entity.FieldValue;
import com.yujl.coder.core.service.GenerateService;
import com.yujl.coder.util.VelocityUtil;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuyj
 */
@Service
public class GenerateServiceImpl implements GenerateService {
    @Override
    public String generate(List<FieldValue> fieldValues, String template) {
        return doGenerator(fieldValues, template);
    }

    private String doGenerator(List<FieldValue> fieldValues, String template) {
        if (template == null) {
            return "";
        }
        VelocityContext context = new VelocityContext();
        for (FieldValue fieldValue : fieldValues) {
            context.put(fieldValue.getFieldName(),fieldValue.getValue());
        }
        return VelocityUtil.generate(context, template);
    }
}
