/**
 * 
 */
package wang.yongrui.wechat.entity.jpa;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PART_ACTION", joinColumns = { @JoinColumn(name = "part_id") }, inverseJoinColumns = {
			@JoinColumn(name = "action_id") })
	private Set<ActionEntity> actionEntitySet;

}
