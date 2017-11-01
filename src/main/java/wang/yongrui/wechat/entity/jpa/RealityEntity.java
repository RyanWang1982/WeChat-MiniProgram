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
import wang.yongrui.wechat.entity.basic.RealityBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "REALITY")
@Getter
@Setter
public class RealityEntity extends RealityBasic {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;

	@OneToMany(mappedBy = "realityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ExerciseEntity> exerciseEntitySet;

}
