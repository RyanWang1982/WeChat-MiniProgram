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
import wang.yongrui.wechat.entity.basic.RealityBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "REALITY")
@Getter
@Setter
public class RealityEntity extends RealityBasic {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "REALITY_EXERCISE", joinColumns = { @JoinColumn(name = "reality_id") }, inverseJoinColumns = {
			@JoinColumn(name = "exercise_id") })
	private Set<ExerciseEntity> exerciseEntitySet;

}
