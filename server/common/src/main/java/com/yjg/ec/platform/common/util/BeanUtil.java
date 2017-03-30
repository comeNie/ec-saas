package com.yjg.ec.platform.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);
	
	/**
	 * obj �? map<String, Object>
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> transToMap(Object obj) {  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                // 过滤class属�??  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();
                    if(null == getter)
                    	continue;
                    Object value = getter.invoke(obj);  
                    if(value!=null)
                    	map.put(key, value);  
                }  
            }  
        } catch (Exception e) {  
        	LOGGER.error("transBean2Map Error " ,e);  
        }  
        return map;  
    }  

}
