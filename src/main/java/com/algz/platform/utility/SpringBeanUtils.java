package com.algz.platform.utility;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;


public abstract class SpringBeanUtils {

	
	/**
	 * Map 转换成 Entity.
	 * 示例： MapToEntity(map,User.class)
	 * @param <T>
	 * @param map Map< String,Object >
	 * @param EntityType Class.forName(xx.xx.Class);XClass.class
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IntrospectionException 
	 */
	public static <T> T MapToEntity(Map<String,Object> map,Class<T> entityType) throws InstantiationException, IllegalAccessException, IntrospectionException, IllegalArgumentException, InvocationTargetException {
        T t = entityType.newInstance();
        
        BeanInfo beanInfo = Introspector.getBeanInfo(entityType);    
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
        for (PropertyDescriptor property : propertyDescriptors) {  
            Method setter = property.getWriteMethod();    
            if (setter != null) {  
                setter.invoke(t, map.get(property.getName()));   
            }  
        }  
        return t;
	}
	
	/**
	 * List< Map > 转换成 List< Entity >. 
	 * @param <T>
	 * @param mapList
	 * @param entityType
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IntrospectionException
	 */
	public static <T> List<T> ListMapToListEntity(List<Map<String,Object>> mapList,Class<T> entityType) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		List<T> list=new ArrayList<T>();
		// 获取迭代器
        Iterator<Map<String,Object>> it = mapList.iterator();
		while(it.hasNext()) {
			list.add(MapToEntity(it.next(),entityType));
		}
		return list;
	}
	
	public static <T> Page<T> PageListMapToPageEntity(Page<Map<String,Object>> pageList,Pageable pageable,Class<T> entityType)throws Exception{
		List<T> list=new ArrayList<T>();
		List<Map<String,Object>> objList=pageList.getContent();
		for(Map<String,Object> obj : objList) {
			list.add(MapToEntity(obj,entityType));
		}
		Page<T> page=new PageImpl<T>(list,pageable,pageList.getTotalPages());
		return page;
	}
	
	/**
	 * 将source中不为空的字段，拷贝(覆盖)到target中
	 * 
	 * 示例： target中的字段String A=1,src中字段A未赋值即为null值，
	 * 在进行copy过程中,自动忽略src字段中的null值不进行操作,保留target的原有值
	 * @param src  被复制对象
	 * @param target  目标对象
	 */
	public static void copyPropertiesForbidNull(Object src, Object target){
	        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null||srcValue.equals("null")) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
	
	
	
//	/**
//     * 将source中不为空的字段，拷贝(覆盖)到target中
//     * @param source
//     * @param target
//     * @throws BeansException
//     */
//    public static void copyProperties(Object source, Object target) throws BeansException {
//        Assert.notNull(source, "Source must not be null");
//        Assert.notNull(target, "Target must not be null");
//        Class<?> actualEditable = target.getClass();
//        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
//        for (PropertyDescriptor targetPd : targetPds) {
//            if (targetPd.getWriteMethod() != null) {
//                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
//                if (sourcePd != null && sourcePd.getReadMethod() != null) {
//                    try {
//                        Method readMethod = sourcePd.getReadMethod();
//                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
//                            readMethod.setAccessible(true);
//                        }
//                        Object value = readMethod.invoke(source);
//                        // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
//                        if (value != null) {
//                            Method writeMethod = targetPd.getWriteMethod();
//                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
//                                writeMethod.setAccessible(true);
//                            }
//                            writeMethod.invoke(target, value);
//                        }
//                    } catch (Throwable ex) {
//                        throw new FatalBeanException("Could not copy properties from source to target", ex);
//                    }
//                }
//            }
//        }
//    }
}
