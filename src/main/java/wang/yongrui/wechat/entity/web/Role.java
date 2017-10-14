/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import static wang.yongrui.wechat.utils.EntityUtils.*;

import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.RoleBasic;
import wang.yongrui.wechat.entity.jpa.PrivilegeEntity;
import wang.yongrui.wechat.entity.jpa.RoleEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy" })
@JsonInclude(value = Include.NON_EMPTY)
public class Role extends RoleBasic implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Set<Privilege> privilegeSet;

	/**
	 * 
	 */
	public Role() {
		super();
	}

	/**
	 * @param roleEntity
	 */
	public Role(RoleEntity roleEntity) {
		super();
		if (null != roleEntity) {
			BeanUtils.copyProperties(roleEntity, this);
			setPrivilegeSet(getObjectSetFromEntitySetWithHeritage(roleEntity.getPrivilegeEntitySet(),
					PrivilegeEntity.class, Privilege.class));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@JsonIgnore
	@Override
	public String getAuthority() {
		return getName();
	}

}
