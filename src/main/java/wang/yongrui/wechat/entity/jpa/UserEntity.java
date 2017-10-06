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

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<RoleEntity> roleEntitySet;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "USER_EXTENDED_INFO", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "extended_info_id") })
	private List<ExtendedInfoEntity> extendedInfoEntityList;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "USER_PLAN", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "plan_id") })
	private Set<PlanEntity> planEntitySet;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "USER_REALITY", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "reality_id") })
	private Set<RealityEntity> realityEntitySet;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "CUSTOMED_PART", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "part_id") })
	private Set<PartEntity> customedPartEntitySet;

}
