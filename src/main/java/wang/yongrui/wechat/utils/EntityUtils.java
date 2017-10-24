/**
 * 
 */
package wang.yongrui.wechat.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author Wang Yongrui
 *
 */
public class EntityUtils {

	/**
	 * 浅拷贝，仅拷贝下一级关联的属性，并不拷贝级联关系
	 * 
	 * @param sourseSet
	 * @param targetClass
	 * @return
	 */
	public static <S, T> Set<T> getTargetSetFromSourceSet(Set<S> sourseSet, Class<T> targetClass) {
		Set<T> targetSet = null;
		if (CollectionUtils.isNotEmpty(sourseSet)) {
			targetSet = new LinkedHashSet<>();
			for (S source : sourseSet) {
				try {
					T target = targetClass.newInstance();
					BeanUtils.copyProperties(source, target);
					targetSet.add(target);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		return targetSet;
	}

	/**
	 * 深拷贝，除下一级关联的属性外，还要拷贝级联关系以及级联关系的属性
	 * 
	 * @param entitySet
	 * @param entityClass
	 * @param objectClass
	 * @return
	 */
	public static <E, O> Set<O> getObjectSetFromEntitySetWithHeritage(Set<E> entitySet, Class<E> entityClass,
			Class<O> objectClass) {
		Set<O> objSet = null;
		if (CollectionUtils.isNotEmpty(entitySet)) {
			objSet = new LinkedHashSet<>();
			for (E entity : entitySet) {
				try {
					O obj = objectClass.getConstructor(entityClass).newInstance(entity);
					BeanUtils.copyProperties(entity, obj);
					objSet.add(obj);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}

		return objSet;
	}

}
