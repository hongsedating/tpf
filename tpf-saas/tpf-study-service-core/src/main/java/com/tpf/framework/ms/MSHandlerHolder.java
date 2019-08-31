package com.tpf.framework.ms;

import com.tpf.framework.core.MethodParameter;
import com.tpf.framework.ms.annotation.MSRequestMapping;
import org.springframework.core.DefaultParameterNameDiscoverer;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class MSHandlerHolder {
    private String[] path;
    private Method method;
    private MethodParameter[] methodParameters;

    public MSHandlerHolder(Method method){
        MSRequestMapping mapping = method.getAnnotation(MSRequestMapping.class);
        this.path = mapping.value();
        this.method = method;
        initMethodParameters();
    }
    public String[] getPath() {
        return path;
    }

    public Method getMethod() {
        return method;
    }
    public Class getBeanClass(){
        return method.getDeclaringClass();
    }

    public MethodParameter[] getParameters(){
        return methodParameters;
    }

    public void initMethodParameters(){
        int count = method.getParameterCount();
        if(count > 0){
            methodParameters = new MethodParameter[count];
            Parameter[] parameters = method.getParameters();
            DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
            String[] names = parameterNameDiscoverer.getParameterNames(method);
            for (int i = 0; i < count; i++) {
                methodParameters[i] = new MethodParameter(parameters[i], names[i], i);
            }
        }
    }
}