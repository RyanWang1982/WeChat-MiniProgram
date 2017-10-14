/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import wang.yongrui.wechat.entity.basic.ExtendedInfoBasic;
import wang.yongrui.wechat.entity.jpa.ExtendedInfoEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy" })
@JsonInclude(value = Include.NON_EMPTY)
public class ExtendedInfo extends ExtendedInfoBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private String name;

	/**
	 * 
	 */
	public ExtendedInfo() {
		super();
	}

	/**
	 * @param extendedInfoEntity
	 */
	public ExtendedInfo(ExtendedInfoEntity extendedInfoEntity) {
		super();
		if (null != extendedInfoEntity) {
			BeanUtils.copyProperties(extendedInfoEntity, this);
		}
	}

}
