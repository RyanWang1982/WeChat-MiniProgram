/**
 * 
 */
package wang.yongrui.wechat.entity.jpa;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

	@OneToOne(cascade = CascadeType.ALL)
	private ActionEntity actionEntity;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "EXERCISE_GROUP", joinColumns = { @JoinColumn(name = "exercise_id") }, inverseJoinColumns = {
			@JoinColumn(name = "group_id") })
	private Set<GroupEntity> groupEntitySet;

}
