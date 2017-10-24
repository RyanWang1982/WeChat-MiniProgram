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

	@OneToOne
	@JoinColumn(name = "action_id")
	private ActionEntity actionEntity;

	@OneToMany(mappedBy = "exerciseEntity", cascade = CascadeType.ALL)
	private Set<GroupEntity> groupEntitySet;

}
