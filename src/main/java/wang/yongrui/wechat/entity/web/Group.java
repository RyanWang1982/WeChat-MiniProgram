/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import wang.yongrui.wechat.entity.basic.GroupBasic;
import wang.yongrui.wechat.entity.jpa.GroupEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy" })
@JsonInclude(value = Include.NON_EMPTY)
public class Group extends GroupBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Group() {
		super();
	}

	/**
	 * @param groupEntity
	 */
	public Group(GroupEntity groupEntity) {
		super();
		if (null != groupEntity) {
			BeanUtils.copyProperties(groupEntity, this);
		}
	}

}
