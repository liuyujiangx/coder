package com.yujl;

import com.yujl.common.exception.Assert;
import com.yujl.common.result.ResponseEnum;
import com.yujl.common.util.MD5;
import org.junit.jupiter.api.Test;


public class MD5Test {
    @Test
    public void md5(){
        String string = MD5.encrypt("string");
        System.out.println(string);

        Assert.equals(MD5.encrypt(string), string, ResponseEnum.LOGIN_PASSWORD_ERROR);

    }
}
