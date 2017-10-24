/**
 * 
 */
package wang.yongrui.wechat.entity.jpa;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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

	@ManyToOne
	@JoinTable(name = "CUSTOMED_ACTION", joinColumns = { @JoinColumn(name = "action_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private UserEntity userEntity;

	@ManyToMany
	@JoinTable(name = "PART_ACTION", joinColumns = { @JoinColumn(name = "action_id") }, inverseJoinColumns = {
			@JoinColumn(name = "part_id") })
	private Set<PartEntity> partEntitySet;

}
