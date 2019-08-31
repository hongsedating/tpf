package com.tpf.framework.ms.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 请求映射
 * @author lingkai 20190711
 * @version 0.0.1
 * */
public @interface MSRequestMapping {
    String[] value() default {};
}
