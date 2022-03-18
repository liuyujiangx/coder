package com.yujl.coder.core;

import com.yujl.coder.core.pojo.properties.CoderProperties;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PropertiesTest {
    @Autowired
    CoderProperties coderProperties;
    @Test
    public void test1(){
        System.out.println(coderProperties.getAllowedOrigins());
    }
}
