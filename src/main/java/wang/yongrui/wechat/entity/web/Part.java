/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.PartBasic;
import wang.yongrui.wechat.entity.jpa.ActionEntity;
import wang.yongrui.wechat.entity.jpa.PartEntity;

/**
 * @author Wang Yongrui
 *
 */
public class Part extends PartBasic {

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
			if (CollectionUtils.isNotEmpty(partEntity.getActionEntitySet())) {
				Set<Action> actionSet = new LinkedHashSet<>();
				for (ActionEntity actionEntity : partEntity.getActionEntitySet()) {
					actionSet.add(new Action(actionEntity));
				}
				setActionSet(actionSet);
			}
		}
	}

}
