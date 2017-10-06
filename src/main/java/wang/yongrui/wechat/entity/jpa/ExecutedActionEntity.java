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
import wang.yongrui.wechat.entity.basic.ActionBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "EXECUTED_ACTION")
@Getter
@Setter
public class ExecutedActionEntity extends ActionBasic {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "EXECUTED_ACTION_GROUP", joinColumns = {
			@JoinColumn(name = "executed_action_id") }, inverseJoinColumns = {
					@JoinColumn(name = "executed_group_id") })
	private Set<ExecutedGroupEntity> executedGroupEntitySet;

}
