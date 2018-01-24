/**
 * 
 */
package wang.yongrui.wechat.entity.jpa;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.ExerciseBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "EXERCISE")
@Getter
@Setter
public class ExerciseEntity extends ExerciseBasic {

	@ManyToOne
	@JoinColumn(name = "circle_day_id")
	private CircleDayEntity circleDayEntity;

	@ManyToOne
	@JoinColumn(name = "reality_id")
	private RealityEntity realityEntity;

	@OneToOne
	@JoinColumn(name = "action_id")
	private ActionEntity actionEntity;

	@OneToMany(mappedBy = "exerciseEntity", cascade = CascadeType.ALL)
	private Set<GroupEntity> groupEntitySet;

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
		result = prime * result + ((actionEntity == null) ? 0 : actionEntity.hashCode());
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
		ExerciseEntity other = (ExerciseEntity) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		if (this.getTarget() != other.getTarget())
			return false;
		if (actionEntity == null) {
			if (other.actionEntity != null)
				return false;
		} else if (!actionEntity.equals(other.actionEntity))
			return false;
		return true;
	}

}
