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
import wang.yongrui.wechat.entity.basic.PartBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "EXECUTED_PART")
@Getter
@Setter
public class ExecutedPartEntity extends PartBasic {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "EXECUTED_PART_ACTION", joinColumns = {
			@JoinColumn(name = "executed_part_id") }, inverseJoinColumns = { @JoinColumn(name = "executed_action_id") })
	private Set<ExecutedActionEntity> executedActionEntitySet;

}
