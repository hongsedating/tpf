package com.tpf.framework.ms.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 微服务Controller注解
 * @author lingkai 20190711
 * @version 0.0.1
 * */
public @interface MSController {
    String value() default "";
}
