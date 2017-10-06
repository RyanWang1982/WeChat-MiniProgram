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
@Entity(name = "ACTION")
@Getter
@Setter
public class ActionEntity extends ActionBasic {

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ACTION_GROUP", joinColumns = { @JoinColumn(name = "action_id") }, inverseJoinColumns = {
			@JoinColumn(name = "group_id") })
	private Set<GroupEntity> groupEntitySet;

}
