/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import static wang.yongrui.wechat.utils.EntityUtils.*;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.CircleDayBasic;
import wang.yongrui.wechat.entity.jpa.CircleDayEntity;
import wang.yongrui.wechat.entity.jpa.ExerciseEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy" })
@JsonInclude(value = Include.NON_EMPTY)
public class CircleDay extends CircleDayBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Set<Exercise> exerciseSet;

	/**
	 * 
	 */
	public CircleDay() {
		super();
	}

	/**
	 * @param circleDayEntity
	 */
	public CircleDay(CircleDayEntity circleDayEntity) {
		super();
		if (null != circleDayEntity) {
			BeanUtils.copyProperties(circleDayEntity, this);
			setExerciseSet(getObjectSetFromEntitySetWithHeritage(circleDayEntity.getExerciseEntitySet(),
					ExerciseEntity.class, Exercise.class));
		}
	}

}
