/**
 *
 */
package wang.yongrui.wechat.entity.jpa;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.RoleBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "ROLE")
@Getter
@Setter
public class RoleEntity extends RoleBasic {

	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private Set<UserEntity> userEntitySet;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ROLE_PRIVILEGE", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "privilege_id") })
	private Set<PrivilegeEntity> privilegeEntitySet;

}
