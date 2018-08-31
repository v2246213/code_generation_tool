package com.ndhc.cloud.logic.mpgenerator.util;

import org.apache.commons.beanutils.BeanMap;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @author yangnian
 * @datc 2018/8/31 14:10
 */
public  class MapUtil {
    /**
     * map转换为对象
     *
     * @param beanClass
     * @param map
     * @return
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        org.apache.commons.beanutils.BeanUtils.populate(obj, map);

        return obj;
    }


        public static Map<?, ?> objectToMap (Object obj){
            if (obj == null)
                return null;

            return new org.apache.commons.beanutils.BeanMap(obj);
        }

    /**
      * 将map装换为javabean对象
      * @param map
      * @param bean
      * @return
      */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
       net.sf.cglib.beans.BeanMap beanMap = net.sf.cglib.beans.BeanMap.create(bean);
      beanMap.putAll(map);
          return bean;
    }


}