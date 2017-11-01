/**
 *
 */
package wang.yongrui.wechat.entity.jpa;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.UserBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "USER")
@Getter
@Setter
public class UserEntity extends UserBasic {

	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<RoleEntity> roleEntitySet;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "USER_EXTENDED_INFO", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "extended_info_id") })
	private List<ExtendedInfoEntity> extendedInfoEntityList;

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PlanEntity> planEntitySet;

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RealityEntity> realityEntitySet;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "CUSTOMED_ACTION", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "action_id") })
	private Set<ActionEntity> customedActionEntitySet;

}
