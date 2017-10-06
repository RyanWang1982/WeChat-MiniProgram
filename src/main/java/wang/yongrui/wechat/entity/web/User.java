/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.UserBasic;
import wang.yongrui.wechat.entity.jpa.ExtendedInfoEntity;
import wang.yongrui.wechat.entity.jpa.PlanEntity;
import wang.yongrui.wechat.entity.jpa.RoleEntity;
import wang.yongrui.wechat.entity.jpa.UserEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "authorities", "password", "accountNonExpired", "accountNonLocked",
		"credentialsNonExpired", "enabled" })
@JsonInclude(value = Include.NON_EMPTY)
public class User extends UserBasic implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6176438686196620165L;

	@Getter
	@Setter
	private Set<Role> roleSet;

	@Getter
	@Setter
	private Map<String, ExtendedInfo> extendedInfoMap;

	@Getter
	@Setter
	private Set<Plan> planSet;

	@Getter
	@Setter
	private Set<Reality> realitySet;

	@Getter
	@Setter
	private Set<Part> partSet;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * 
	 */
	public User(UserEntity userEntity) {
		super();
		if (null != userEntity) {
			BeanUtils.copyProperties(userEntity, this);
			if (CollectionUtils.isNotEmpty(userEntity.getRoleEntitySet())) {
				Set<Role> roleSet = new LinkedHashSet<>();
				for (RoleEntity roleEntity : userEntity.getRoleEntitySet()) {
					roleSet.add(new Role(roleEntity));
				}
				setRoleSet(roleSet);
			}
			if (CollectionUtils.isNotEmpty(userEntity.getExtendedInfoEntityList())) {
				Map<String, ExtendedInfo> extendedInfoMap = new HashMap<>();
				for (ExtendedInfoEntity extendedInfoEntity : userEntity.getExtendedInfoEntityList()) {
					extendedInfoMap.put(extendedInfoEntity.getName(), new ExtendedInfo(extendedInfoEntity));
				}
				setExtendedInfoMap(extendedInfoMap);
			}
			if (CollectionUtils.isNotEmpty(userEntity.getPlanEntitySet())) {
				Set<Plan> planSet = new LinkedHashSet<>();
				for (PlanEntity planEntity : userEntity.getPlanEntitySet()) {
					planSet.add(new Plan(planEntity));
				}
				setPlanSet(planSet);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return this.getLoginName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities(
	 * )
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getRoleSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
