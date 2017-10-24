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

	@ManyToOne
	@JoinColumn(name = "plan_id")
	private PlanEntity planEntity;

	@OneToMany(mappedBy = "circleDayEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ExerciseEntity> exerciseEntitySet;

}
