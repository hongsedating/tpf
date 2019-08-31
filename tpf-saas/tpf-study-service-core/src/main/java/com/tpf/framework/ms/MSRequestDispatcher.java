package com.tpf.framework.ms;

import com.tpf.framework.core.MethodParameter;
import com.tpf.framework.core.annotation.NotNull;
import com.tpf.framework.exception.BusinessException;
import com.tpf.study.init.ApplicationManager;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * */
public class MSRequestDispatcher {
    private MSRequestDispatcher(){
    }
    public static String dispatch(String path, String data) throws InvocationTargetException, IllegalAccessException {
        MSHandlerHolder holder = MSRequestMappingRegistry.getHandler(path);
        if(holder == null){
            throw new BusinessException("非法请求路径："+path);
        }
        //适配处理器
        return invokeHandler(holder, data);
    }
    /**
     * 适配并调用处理器
     * */
    private static String invokeHandler(MSHandlerHolder holder, String data) throws InvocationTargetException, IllegalAccessException {
        Method method = holder.getMethod();
        MethodParameter[] parameters = holder.getParameters();
        List<Object> args = null;
        if(parameters != null && parameters.length != 0){
            JSONObject param = null;
            try {
                param = JSONObject.fromObject(data);
            }catch (Exception e){
                throw new BusinessException("param is not a Json");
            }
            args = new ArrayList<>();
            //校验参数
            for (MethodParameter parameter : parameters) {
                Object arg = null;
                if(param.containsKey(parameter.getName())){
                    arg = param.get(parameter.getName());
                }
                if(parameter.isAnnotationPresent(NotNull.class) && arg == null){
                    throw new BusinessException(method.getName()+"参数 "+parameter.getName()+" 不能为空：");
                }
                args.add(arg);
            }
        }
        ApplicationContext ac = ApplicationManager.getApplicationContext();
        Object controller = ac.getBean(holder.getBeanClass());
        return (String) method.invoke(controller, args.toArray());
    }

}
