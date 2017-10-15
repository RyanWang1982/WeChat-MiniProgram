/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import static wang.yongrui.wechat.utils.EntityUtils.*;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.PartBasic;
import wang.yongrui.wechat.entity.jpa.PartEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy" })
@JsonInclude(value = Include.NON_EMPTY)
public class Part extends PartBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Set<Action> actionSet;

	/**
	 * 
	 */
	public Part() {
		super();
	}

	/**
	 * @param partEntity
	 */
	public Part(PartEntity partEntity) {
		super();
		if (null != partEntity) {
			BeanUtils.copyProperties(partEntity, this);
			setActionSet(getTargetSetFromSourceSet(partEntity.getActionEntitySet(), Action.class));
		}
	}

}
