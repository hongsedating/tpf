package com.tpf.framework.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public class MethodParameter {
    private Parameter parameter;
    private String name;
    private int index;
    public MethodParameter(Parameter parameter, String name, int index){
        this.index = index;
        this.parameter = parameter;
        this.name = name;
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return parameter.getAnnotation(annotationClass) != null;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
