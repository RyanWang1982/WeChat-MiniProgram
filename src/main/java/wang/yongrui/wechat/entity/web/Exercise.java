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
import wang.yongrui.wechat.entity.basic.ExerciseBasic;
import wang.yongrui.wechat.entity.jpa.ExerciseEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy" })
@JsonInclude(value = Include.NON_EMPTY)
public class Exercise extends ExerciseBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Action action;

	@Getter
	@Setter
	private Set<Group> groupSet;

	/**
	 * 
	 */
	public Exercise() {
		super();
	}

	/**
	 * @param exerciseEntity
	 */
	public Exercise(ExerciseEntity exerciseEntity) {
		super();
		if (null != exerciseEntity) {
			BeanUtils.copyProperties(exerciseEntity, this);
			if (null != exerciseEntity.getActionEntity()) {
				Action action = new Action(exerciseEntity.getActionEntity());
				setAction(action);
			}
			setGroupSet(getTargetSetFromSourceSet(exerciseEntity.getGroupEntitySet(), Group.class));
		}
	}

}
