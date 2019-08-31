package com.tpf.framework.common;

import java.util.HashMap;
import java.util.Map;

public class ThreadParameter {
    private static final ThreadLocal threadLocal = new ThreadLocal()
    {
        private Map map;

        protected Object initialValue()
        {
            map = new HashMap();
            return map;
        }
    };

    public static void set(String key, Object value)	  {
        Map map = (Map)threadLocal.get();
        map.put(key, value);
    }

    public static Object get(String key)  {
        Map map = (Map)threadLocal.get();
        return map.get(key);
    }

    public static void remove()	  {
        Map map = (Map)threadLocal.get();
        map.clear();
        threadLocal.set(map);
    }
}
