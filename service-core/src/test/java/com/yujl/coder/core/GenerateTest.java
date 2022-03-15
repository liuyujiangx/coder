package com.yujl.coder.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yujl.coder.core.pojo.entity.FieldValue;
import com.yujl.coder.core.service.GenerateService;
import com.yujl.coder.core.service.impl.GenerateServiceImpl;
import jdk.internal.instrumentation.TypeMapping;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
@SpringBootTest
@RunWith(SpringRunner.class)
public class GenerateTest {
    @Resource
    private GenerateService generateService;

    @Test
    void test1() {
        List<FieldValue> fieldValues = new LinkedList<>();
        FieldValue fieldValue = new FieldValue();
        String template = "private ${entity.id} ${entity.name} ${entity.age};#if(${entity.isChild}) isChild #end";
        JSONObject jsonObject = (JSONObject) JSON.parse("{'name':'liu', id:'1', 'age':'19', 'isChild':false}");
        Map<String, Object> value = new HashMap<>();
        value.put("id", "1");
        value.put("name", "liu");
        value.put("age", "19");
        value.put("isChild", false);
        fieldValue.setValue(jsonObject);
        fieldValue.setFieldId(1L);
        fieldValue.setFieldName("entity");
        fieldValues.add(fieldValue);
        String generate = generateService.generate(fieldValues, template);
        System.out.println(generate);
    }
    @Test
    void test2(){
        Map<String, Object> value = new HashMap<>();
        value.put("id", "1");
        value.put("name", "liu");
        value.put("age", "19");
        value.put("isChild", false);
        System.out.println(value);
    }
}
