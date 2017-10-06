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
@Entity(name = "PART")
@Getter
@Setter
public class PartEntity extends PartBasic {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "PART_ACTION", joinColumns = { @JoinColumn(name = "part_id") }, inverseJoinColumns = {
			@JoinColumn(name = "action_id") })
	private Set<ActionEntity> actionEntitySet;

}
