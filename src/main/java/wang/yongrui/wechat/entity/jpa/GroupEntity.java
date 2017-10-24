/**
 * 
 */
package wang.yongrui.wechat.entity.jpa;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.GroupBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "GRP")
@Getter
@Setter
public class GroupEntity extends GroupBasic {

	@ManyToOne
	@JoinColumn(name = "exercise_id")
	private ExerciseEntity exerciseEntity;

}
