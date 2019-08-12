package com.taoqy.util;

import org.reflections.Reflections;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 实体相关的工具方法
 *
 * @author guoyka
 * @version 1.0, 2018/4/20
 */
public class EntityHelper {

    protected static final org.slf4j.Logger log = LoggerFactory.getLogger(EntityHelper.class);

    /**
     * 将实体实例转化成map
     * @param entity 实体对象
     * @return [fieldName: fieldValue]
     */
    public static Map<String, Object> toMap(Object entity){
        if (entity == null) {
            return null;
        } else {
            Map<String, Object> map = new HashMap<>();
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : propertyDescriptors) {
                    String key = property.getName();
                    // 过滤class属性
                    if (!key.equals("class")) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(entity);
                        map.put(key, value);
                    }

                }
            } catch (Exception e) {
                System.out.println("transBeanToMap Error --> " + e.getLocalizedMessage());
                e.printStackTrace();
            }
            return map;
        }
    }

    /**
     * 寻找指定包被指定注解修饰的类
     * @param packageName 包名
     * @param annotation 注解类
     * @return 类集合
     */
    public static Set<Class<?>> getPackageClassByAnnotation(String packageName, Class<? extends Annotation> annotation){
        Reflections reflections = new Reflections(packageName);
        return reflections.getTypesAnnotatedWith(annotation);
    }

    private EntityHelper(){}
}
