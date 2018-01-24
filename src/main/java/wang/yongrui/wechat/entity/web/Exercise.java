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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		result = prime * result + ((this.getTarget() == null) ? 0 : this.getTarget().hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exercise other = (Exercise) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		if (this.getTarget() != other.getTarget())
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		return true;
	}

}
