/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import wang.yongrui.wechat.entity.basic.PrivilegeBasic;
import wang.yongrui.wechat.entity.jpa.PrivilegeEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy" })
@JsonInclude(value = Include.NON_EMPTY)
public class Privilege extends PrivilegeBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Privilege() {
		super();
	}

	/**
	 * @param privilegeEntity
	 */
	public Privilege(PrivilegeEntity privilegeEntity) {
		super();
		if (null != privilegeEntity) {
			BeanUtils.copyProperties(privilegeEntity, this);
		}
	}

}
