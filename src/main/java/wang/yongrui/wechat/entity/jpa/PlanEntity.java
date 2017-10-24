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
import wang.yongrui.wechat.entity.basic.PlanBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "PLAN")
@Getter
@Setter
public class PlanEntity extends PlanBasic {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;

	@OneToMany(mappedBy = "planEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CircleDayEntity> circleDayEntitySet;

}
