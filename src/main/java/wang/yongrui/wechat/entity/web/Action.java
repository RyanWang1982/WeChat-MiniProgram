/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import static wang.yongrui.wechat.utils.EntityUtils.*;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.metamodel.Attribute;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.ActionBasic;
import wang.yongrui.wechat.entity.jpa.ActionEntity;
import wang.yongrui.wechat.entity.jpa.ActionEntity_;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy" })
@JsonInclude(value = Include.NON_EMPTY)
public class Action extends ActionBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private User user;

	@Getter
	@Setter
	private Set<Part> partSet;

	/**
	 * 
	 */
	public Action() {
		super();
	}

	/**
	 * @param actionEntity
	 */
	public Action(ActionEntity actionEntity) {
		super();
		if (null != actionEntity) {
			BeanUtils.copyProperties(actionEntity, this);
		}
	}

	/**
	 * @param actionEntity
	 * @param includedAttributeSet
	 */
	public Action(ActionEntity actionEntity, Set<Attribute<?, ?>> includedAttributeSet) {
		super();
		if (null != actionEntity) {
			BeanUtils.copyProperties(actionEntity, this);
			if (includedAttributeSet.contains(ActionEntity_.partEntitySet)) {
				setPartSet(getTargetSetFromSourceSet(actionEntity.getPartEntitySet(), Part.class));
			}
		}
	}

}
