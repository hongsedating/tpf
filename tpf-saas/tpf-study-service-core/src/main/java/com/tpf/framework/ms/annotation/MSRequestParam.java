package com.tpf.framework.ms.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MSRequestParam {
    String value() default "";
    boolean required() default true;
    String defaultValue();
}
