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
import wang.yongrui.wechat.entity.basic.PlanBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "PLAN")
@Getter
@Setter
public class PlanEntity extends PlanBasic {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "PLAN_CIRCLE_DAY", joinColumns = { @JoinColumn(name = "plan_id") }, inverseJoinColumns = {
			@JoinColumn(name = "circle_day_id") })
	private Set<CircleDayEntity> circleDayEntitySet;

}
