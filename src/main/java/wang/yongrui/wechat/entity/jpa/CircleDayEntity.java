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

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.CircleDayBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "CIRCLE_DAY")
@Getter
@Setter
public class CircleDayEntity extends CircleDayBasic {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "CIRCLE_DAY_EXERCISE", joinColumns = {
			@JoinColumn(name = "circle_day_id") }, inverseJoinColumns = { @JoinColumn(name = "exercise_id") })
	private Set<ExerciseEntity> exerciseEntitySet;

	// @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	// @JoinTable(name = "CIRCLE_DAY_PART", joinColumns = { @JoinColumn(name =
	// "circle_day_id") }, inverseJoinColumns = {
	// @JoinColumn(name = "part_id") })
	// private Set<PartEntity> partEntitySet;

}
