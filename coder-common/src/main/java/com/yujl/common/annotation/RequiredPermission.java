package com.yujl.common.annotation;

import java.lang.annotation.*;

/**
 * @author liuyj
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequiredPermission {
    String value();
}
