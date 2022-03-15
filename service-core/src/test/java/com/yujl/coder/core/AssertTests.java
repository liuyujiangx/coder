package com.yujl.coder.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertTests {

    @Test
    public void test1(){
        Object o = "da";
        Assertions.assertNotNull(o, "用户不存在");
    }

}
